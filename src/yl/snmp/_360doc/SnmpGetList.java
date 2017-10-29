package yl.snmp._360doc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SnmpGetList {
	
	public static final int DEFAULT_VERSION = SnmpConstants.version2c;
	public static final String DEFAULT_PROTOCOL = "udp";
	public static final int DEFAULT_PORT = 161;
	public static final long DEFAULT_TIMEOUT = 3 * 1000L;
	public static final int DEFAULT_RETRY = 3;
	
	public static CommunityTarget createDefault(String ip, String community) {
		Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip + "/" + DEFAULT_PORT);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(community));
		target.setAddress(address);
		target.setVersion(DEFAULT_VERSION);
		target.setTimeout(DEFAULT_TIMEOUT);
		target.setRetries(DEFAULT_RETRY);
		return target;
	}
	
	public static void snmpGetList(String ip, String community, List<String> oidList) {
		CommunityTarget target = createDefault(ip, community);
		Snmp snmp = null;
		try {
			PDU pdu = new PDU();
			for (String oidStr : oidList) {
				pdu.add(new VariableBinding(new OID(oidStr)));
			}
			DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
			transport.listen();
			snmp = new Snmp(transport);
			System.out.println("---发送消息---");
			pdu.setType(PDU.GET);
			ResponseEvent responseEvent = snmp.send(pdu, target);
			System.out.println("PeerAddress:" + responseEvent.getPeerAddress());
			PDU response = responseEvent.getResponse();
			if (response == null) {
				System.out.println("response is null !!!");
			}
			else {
				System.out.println("response pdu size is " + response.size());
				for (int i = 0; i < response.size(); i++) {
					VariableBinding vb = response.get(i);
					System.out.println(vb.getOid() + " = " + vb.getVariable());
				}
				System.out.println("SNMP GET 列表完成!");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (snmp != null) {
				try {
					snmp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String ip = "192.168.8.44"; // localhost 192.168.8.44
		ip = "localhost";
		String community = "public";
		// 测试常用oid, http://skykws8023.blog.51cto.com/4514277/1543362
		List<String> list = new ArrayList<String>();
		list.add("1.3.6.1.2.1.1.1.0"); // 获取系统基本信息
		list.add("1.3.6.1.2.1.1.3.0"); // 监控时间
		list.add("1.3.6.1.2.1.1.4.0"); // 系统联系人
		list.add("1.3.6.1.2.1.1.5.0"); // 获取机器名
		list.add("1.3.6.1.2.1.1.6.0"); // 机器坐在位置
		list.add("1.3.6.1.2.1.1.7.0"); // 机器提供的服务
		
		list.add("1.3.6.1.2.1.2.1.0"); // 网络接口的数目
		list.add("1.3.6.1.4.1.2021.11.9.0"); // 用户CPU百分比
		list.add("1.3.6.1.4.1.2021.11.10.0"); // 系统CPU百分比
		list.add("1.3.6.1.4.1.2021.11.11.0"); // 空闲CPU百分比
		list.add("1.3.6.1.4.1.2021.11.50.0"); // 原始用户CPU使用时间
		list.add("1.3.6.1.4.1.2021.11.51.0"); // 原始nice占用时间
		list.add("1.3.6.1.4.1.2021.11.52.0"); // 原始系统CPU使用时间
		list.add("1.3.6.1.4.1.2021.11.53.0"); // 原始CPU空闲时间
		list.add("1.3.6.1.4.1.2021.11.3.0");
		
		list.add("1.3.6.1.2.1.25.2.2.0"); // 获取内存大小
		list.add("1.3.6.1.4.1.2021.4.3.0"); // Total Swap Size(虚拟内存)
		
//		List<Map.Entry<String, String>> el = new ArrayList<Map.Entry<String,String>>();
//		el.add(getEntry("获取系统基本信息", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("获取系统基本信息", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("获取系统基本信息", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("获取系统基本信息", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("获取系统基本信息", "1.3.6.1.2.1.1.1.0"));
		SnmpGetList.snmpGetList(ip, community, list);
	}
	
	private static Map.Entry<String, String> getEntry(final String key,final  String value) {
		return new Map.Entry<String, String>() {
			public String getKey() {
				return key;
			}
			public String getValue() {
				return value;
			}
			public String setValue(String value) {
				return value;
			}
		};
	}

}
