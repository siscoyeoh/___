package yl.snmp;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/// http://www.cnblogs.com/xdp-gacl/p/4187089.html
/// 获取远程计算机的名称
public class Snmp4jFirstDemo {
	
	private Snmp snmp = null;
	private int version;
	
	public Snmp4jFirstDemo(int version) {
		this.version = version;
		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			if (version == SnmpConstants.version3) {
				// 设置安全模式
				SecurityProtocols securityProtocols = SecurityProtocols.getInstance();
				OctetString localEngineID = new OctetString(MPv3.createLocalEngineID());
				int engineBoots = 0;
				USM usm = new USM(securityProtocols, localEngineID, engineBoots);
				SecurityModels.getInstance().addSecurityModel(usm);
			}
			// 开始监听消息
			transport.listen();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Boolean syn, final Boolean bro, PDU pdu, String addr) throws IOException {
		// 生成目标地址对象
		Address targetAddress = GenericAddress.parse(addr);
		Target target = null;
		if (version == SnmpConstants.version3) {
			// 添加用户
			// userName
			OctetString userName = new OctetString("MD5DES");
			// user , ,,, 
			OctetString securityName = new OctetString("MD5DES");
			OID authenticationProtocol = AuthMD5.ID;
			OctetString authenticationPassphrase = new OctetString("MD5DESUserAuthPassword");
			OID privacyProtocol = PrivDES.ID;
			OctetString privacyPassphrase = new OctetString("MD5DESUserPrivPassword");
			UsmUser user = new UsmUser(securityName, authenticationProtocol, 
					authenticationPassphrase, privacyProtocol, privacyPassphrase);
			snmp.getUSM().addUser(userName, user);
			target = new UserTarget();
			// 设置安全级别
			((UserTarget) target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
			((UserTarget) target).setSecurityName(new OctetString("MD5DES"));
			target.setVersion(SnmpConstants.version3);
		}
		else {
			target = new CommunityTarget();
			if (version == SnmpConstants.version1) {
				target.setVersion(SnmpConstants.version1);
				((CommunityTarget) target).setCommunity(new OctetString("public"));
			}
			else {
				target.setVersion(SnmpConstants.version2c);
				((CommunityTarget) target).setCommunity(new OctetString("public"));
			}
		}
		// 目标对象相关设置
		target.setAddress(targetAddress);
		target.setRetries(5);
		target.setTimeout(1000);
		
		if (!syn) {
			// 发送报文, 并且接收响应
			ResponseEvent response = snmp.send(pdu, target);
			// 处理响应
			System.out.println("Synchronize(同步) message(消息) from(来自) "
					+ response.getPeerAddress() + "\r\n"+"request(发送的请求):"
					+ response.getRequest() + "\r\n"+"response(返回的响应):"
					+ response.getResponse());
		}
		else {
			// 设置监听对象
			ResponseListener listener = new ResponseListener() {
				
				@Override
				public void onResponse(ResponseEvent event) {
					if (bro.equals(false)) {
						((Snmp) event.getSource()).cancel(event.getRequest(), this);
					}
					// 处理响应
					PDU request = event.getRequest();
					PDU response = event.getRequest();
					System.out.println("Asynchronise(异步) message(消息) from(来自) "
							 + event.getPeerAddress() + "\r\n"+"request(发送的请求):" + request
							 + "\r\n"+"response(返回的响应):" + response);
				}
			};
			// 发送报文
			snmp.send(pdu, target, null, listener);
		}
	}

	public static void main(String[] args) {
		// Snmp的三个版本号
//		int ver3 = SnmpConstants.version3;
		int ver2c = SnmpConstants.version2c;
//		int ver1 = SnmpConstants.version1;
		Snmp4jFirstDemo manager = new Snmp4jFirstDemo(ver2c);
		// 构造报文
		PDU pdu = new PDU();
//		PDU pdu = new ScopedPDU();
		// 设置要获取的对象ID, 这个OID代表远程计算机的名称
		OID oids = new OID("1.3.6.1.2.1.1.5.0");
		VariableBinding vb = new VariableBinding(oids);
		pdu.add(vb);
		// 设置报文类型
		pdu.setType(PDU.GET);
//		((ScopedPDU) pdu).setContextName(new OctetString("priv"));
		try {
			// 发送消息, 其中最后一个是想要发送的目标地址
//			manager.sendMessage(false, true, pdu, "udp:192.168.1.229/161");//192.168.1.229 Linux服务器
//			manager.sendMessage(false, true, pdu, "udp:192.168.8.44/161");//192.168.1.233 WinServer2008服务器
			manager.sendMessage(false, true, pdu, "udp:192.168.8.44/161");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
