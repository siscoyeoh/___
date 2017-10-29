package yl.snmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class SnmpLogicTable {

	public PDU getPDU(String stOID, int iType) {
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID(stOID)));
		pdu.setType(iType);
		return pdu;
	}

	public Target getTarget(String stIP) {
		Address targetAddress = GenericAddress.parse(stIP);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setVersion(SnmpConstants.version2c);
		target.setTimeout(1000);
		target.setRetries(1);
		return target;
	}

	public String getSNMP(String stIP, String stPort, String stOID, int iType) {
		Target target = getTarget(stIP + "/" + stPort);
		String getvalue = null;
		try {
			// Example for Sending an Asynchronous Message
			DefaultUdpTransportMapping udpTransportMap = new DefaultUdpTransportMapping();

			// udpTransportMap.listen();
			Snmp snmp = new Snmp(udpTransportMap);
			snmp.listen();

			PDUFactory pf = new DefaultPDUFactory(PDU.GET);
			TableUtils tu = new TableUtils(snmp, pf);
			OID[] columns = new OID[1];
			columns[0] = new VariableBinding(new OID(stOID)).getOid();
			List list = tu.getTable(target, columns, null, null);
			for (int i = 0; i < list.size(); i++) {
				// System.out.println(i+"======I====");
				TableEvent te = (TableEvent) list.get(i);
				VariableBinding[] vb = te.getColumns();
//				if (vb != null) {
					for (int j = 0; j < vb.length; j++) {
						// System.out.println(j+"=====J======");
						System.out.println(vb[j]);
						// System.out.println(vb[j].getVariable());
					}
//				}
			}

			snmp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getvalue;
	}
	
	public void getDiskInfo(String stIP, String stPort, String stOID) {
		int iType = PDU.GET;
		Target target = getTarget(stIP + "/" + stPort);
		String getvalue = null;
		List<Object> total = new ArrayList<Object>();
		try {
			// 发送异步消息示例
			DefaultUdpTransportMapping udpTransportMapping = new DefaultUdpTransportMapping();
//			udpTransportMapping.listen();
			Snmp snmp = new Snmp(udpTransportMapping);
			snmp.listen();
			PDUFactory pduFactory = new DefaultPDUFactory(PDU.GET);
			TableUtils tableUtils = new TableUtils(snmp, pduFactory);
			OID[] columns = new OID[1];
			columns[0] = new VariableBinding(new OID(stOID)).getOid();
			List<TableEvent> eventList = tableUtils.getTable(target, columns, null, null);
			for (int i = 0; i < eventList.size(); i++) {
				TableEvent event = eventList.get(i);
				System.out.println("\n" + event);
				VariableBinding[] vbs = event.getColumns();
				for (int j = 0; j < vbs.length; j++) {
					System.out.println(vbs[j]);
				}
			}
			snmp.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SnmpLogicTable snmp = new SnmpLogicTable();
		String stIP = "localhost";
		stIP = "192.168.8.115";
		stIP = "127.0.0.1";
//		stIP = "192.168.8.255";
//		String ret = snmp.getSNMP(stIP, "161", ".1.3.6.1.2.1.25.2.3", PDU.GET);
		String oid = ".1.3.6.1.2.1.25.2.3";
//		oid = "";
		snmp.getDiskInfo(stIP, "161", oid);
	}

}
