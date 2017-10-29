package yl.snmp;

import java.io.IOException;

import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
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

public class MainTest {

	public static void main(String[] args) throws IOException {
		// ���ù�����̵�IP�Ͷ˿�
		Address targetAddress = GenericAddress.parse("udp:192.168.8.44/161");
		TransportMapping transport = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(transport);
		USM usm = new USM(SecurityProtocols.getInstance(),
				new OctetString(MPv3.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		transport.listen();
		// ���������û�
		OctetString userName = new OctetString("ray");
		
//		OctetString securityName = new OctetString("ray");
//		OID authenticationProtocol = AuthMD5.ID;
//		OID authenticationPassphrase = PrivDES.ID;
//		OctetString privacyProtocol = new OctetString("xxx");
//		OctetString privacyPassphrase = new OctetString("xxx");
//		UsmUser user = new UsmUser(securityName, authenticationProtocol, authenticationPassphrase, privacyProtocol, privacyPassphrase);
//		snmp.getUSM().addUser(userName, user);
		snmp.getUSM().addUser(
			    new OctetString("ray"),
			    new UsmUser(new OctetString("ray"), AuthMD5.ID,
			      new OctetString("xxx"), PrivDES.ID,
			      new OctetString("xxx")));
		
		// ����target
		 // CommunityTarget target = new CommunityTarget();
		  UserTarget target = new UserTarget();
		  target.setAddress(targetAddress);
		    target.setRetries(2);// ͨ�Ų��ɹ�ʱ�����Դ���
		  target.setTimeout(1500); // ��ʱʱ��
		  target.setVersion(SnmpConstants.version3);  // snmp�汾
		  target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
		  target.setSecurityName(new OctetString("ray"));
		  
		  //���� PDU
		  // PDU pdu = new PDU();
		  PDU pdu = new ScopedPDU();
		  // pdu.setRequestID((new Integer32(1234)));
		  pdu.setErrorIndex(2);
		  // pdu.add(new VariableBinding(new
		  // OID(".1.3.6.1.4.1.15227.1.3.1.1.1.0"),
		  // new OctetString("cpuUsage")));
		  pdu.add(new VariableBinding(new OID(".1.3.6.1.4.1.15227.1.4.1.1.1.0")));
		  
		  pdu.setType(PDU.GET);//���ݻ�ȡ��ʽΪGET
		  
		  
	}

}
