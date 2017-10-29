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
			System.out.println("---������Ϣ---");
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
				System.out.println("SNMP GET �б����!");
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
		// ���Գ���oid, http://skykws8023.blog.51cto.com/4514277/1543362
		List<String> list = new ArrayList<String>();
		list.add("1.3.6.1.2.1.1.1.0"); // ��ȡϵͳ������Ϣ
		list.add("1.3.6.1.2.1.1.3.0"); // ���ʱ��
		list.add("1.3.6.1.2.1.1.4.0"); // ϵͳ��ϵ��
		list.add("1.3.6.1.2.1.1.5.0"); // ��ȡ������
		list.add("1.3.6.1.2.1.1.6.0"); // ��������λ��
		list.add("1.3.6.1.2.1.1.7.0"); // �����ṩ�ķ���
		
		list.add("1.3.6.1.2.1.2.1.0"); // ����ӿڵ���Ŀ
		list.add("1.3.6.1.4.1.2021.11.9.0"); // �û�CPU�ٷֱ�
		list.add("1.3.6.1.4.1.2021.11.10.0"); // ϵͳCPU�ٷֱ�
		list.add("1.3.6.1.4.1.2021.11.11.0"); // ����CPU�ٷֱ�
		list.add("1.3.6.1.4.1.2021.11.50.0"); // ԭʼ�û�CPUʹ��ʱ��
		list.add("1.3.6.1.4.1.2021.11.51.0"); // ԭʼniceռ��ʱ��
		list.add("1.3.6.1.4.1.2021.11.52.0"); // ԭʼϵͳCPUʹ��ʱ��
		list.add("1.3.6.1.4.1.2021.11.53.0"); // ԭʼCPU����ʱ��
		list.add("1.3.6.1.4.1.2021.11.3.0");
		
		list.add("1.3.6.1.2.1.25.2.2.0"); // ��ȡ�ڴ��С
		list.add("1.3.6.1.4.1.2021.4.3.0"); // Total Swap Size(�����ڴ�)
		
//		List<Map.Entry<String, String>> el = new ArrayList<Map.Entry<String,String>>();
//		el.add(getEntry("��ȡϵͳ������Ϣ", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("��ȡϵͳ������Ϣ", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("��ȡϵͳ������Ϣ", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("��ȡϵͳ������Ϣ", "1.3.6.1.2.1.1.1.0"));
//		el.add(getEntry("��ȡϵͳ������Ϣ", "1.3.6.1.2.1.1.1.0"));
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
