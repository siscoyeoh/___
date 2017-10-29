package yl.snmp._360doc;

import java.io.IOException;
import java.util.Vector;

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

// http://www.360doc.com/content/14/1216/20/9043924_433457857.shtml
public class SnmpGet {
	
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
	
	public static void snmpGet(String ip, String community, String oid) {
		CommunityTarget target = createDefault(ip, community);
		Snmp snmp = null;
		try {
			PDU pdu = new PDU();
//			pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.2")));
			pdu.add(new VariableBinding(new OID(oid)));
			DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			System.out.println("------发送PDU------");
			pdu.setType(PDU.GET);
			// 发送前pdu的属性:
			// GET[requestID=0, errorStatus=Success(0), errorIndex=0, VBS[1.3.6.1.2.1.1.1.0 = Null]]
			ResponseEvent responseEvent = snmp.send(pdu, target);
			System.out.println("PeerAddress:" + responseEvent.getPeerAddress());
			PDU response = responseEvent.getResponse();
			if (response == null) {
				System.out.println("response is null, request time out.");
			}
			else {
//				Vector<VariableBinding> vbVect = 
//						(Vector<VariableBinding>) response.getVariableBindings();
//				System.out.println("vb size:" + vbVect.size());
//				if (vbVect.size() == 0) {
//					System.out.println("response vb size is 0");
//				}
//				else {
//					VariableBinding vb = vbVect.firstElement();
//					System.out.println(vb.getOid() + " = " + vb.getVariable());
//				}
				System.out.println("response pdu size is " + response.size());
				for (int i = 0; i < response.size(); i++) {
					VariableBinding vb = response.get(i);
					System.out.println(vb.getOid() + " = " + vb.getVariable());
				}
			}
			System.out.println("SNMP GET一个OID完成.");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String ip = "192.168.8.44"; // localhost 192.168.8.44
		ip = "localhost";
		String community = "public";
		String oidVal = "1.3.6.1.2.1.1.1.0";
//		oidVal = "1.3.6.1.2.1.1.1";
//		oidVal = "1.3.6.1.4.1.2021.1";
		SnmpGet.snmpGet(ip, community, oidVal);
	}

}
