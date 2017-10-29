package yl.snmp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.Null;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class AsynWalk {
  
    public static final int DEFAULT_VERSION = SnmpConstants.version2c;
    public static final String DEFAULT_PROTOCOL = "udp";
    public static final int DEFAULT_PORT = 161;
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;
    public static final int DEFAULT_RETRY = 3;
  
    /** 
     * 创建对象communityTarget 
     * 
     * @param targetAddress 
     * @param community 
     * @param version 
     * @param timeOut 
     * @param retry 
     * @return CommunityTarget 
     */  
    public static CommunityTarget createDefault(String ip, String community) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip  
                + "/" + DEFAULT_PORT);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(DEFAULT_VERSION);
        target.setTimeout(DEFAULT_TIMEOUT);// milliseconds  
        target.setRetries(DEFAULT_RETRY);
        return target;
    }
    
  private static boolean checkWalkFinished(OID targetOID, PDU pdu,  
          VariableBinding vb) {
      boolean finished = false;
      if (pdu.getErrorStatus() != 0) {
          System.out.println("[true] responsePDU.getErrorStatus() != 0 ");
          System.out.println(pdu.getErrorStatusText());
          finished = true;
      }else if (vb.getOid() == null) {
          System.out.println("[true] vb.getOid() == null");
          finished = true;
      }else if (vb.getOid().size() < targetOID.size()) {
          System.out.println("[true] vb.getOid().size() < targetOID.size()");
          finished = true;
      }else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
          System.out.println("[true] targetOID.leftMostCompare() != 0");
          finished = true;
      }else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
          System.out  
                  .println("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
          finished = true;
      }else if (vb.getOid().compareTo(targetOID) <= 0) {
          System.out.println("[true] Variable received is not "  
                  + "lexicographic successor of requested " + "one:");
          System.out.println(vb.toString() + " <= " + targetOID);
          finished = true;
      }
      return finished;

  }
	
    /*异步获取表格*/  
    public static void snmpAsynWalk(String ip, String community, String oid)  
    {
        final CommunityTarget target = createDefault(ip, community);
        Snmp snmp = null;
        try {
            System.out.println("----> demo start <----");
  
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
  
            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));
  
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
  
                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // System.out.println("[request]:" + request);
                        if (response == null) {
                            System.out.println("[ERROR]: response is null");
                        }else if (response.getErrorStatus() != 0) {
                            System.out.println("[ERROR]: response status"  
                                    + response.getErrorStatus() + " Text:"  
                                    + response.getErrorStatusText());
                        }else {
                            System.out  
                                    .println("Received Walk response value :");
                            VariableBinding vb = response.get(0);
  
                            boolean finished = checkWalkFinished(targetOID, pdu, vb);
                            if (!finished) {
                                System.out.println(vb.getOid() + " = "  
                                        + vb.getVariable());
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target,  
                                        null, this);
                            }else {
                                System.out  
                                        .println("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }
  
                }
            };
  
            snmp.getNext(pdu, target, null, listener);
            System.out.println("pdu 已发送,等到异步处理结果...");
  
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
            snmp.close();
  
            System.out.println("----> demo end <----");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Asyn Walk Exception:" + e);
        }
    }

	public static void main(String[] args) {
        String ip = "127.0.0.1";
//      ip = "192.168.8.115";
      String community = "public";
      String oid = ".1.3.6.1.2.1.25.4.2.1.2";// 系统运行的进程列表
      oid = "1.3.6.1.2.1.25.6.3.1.2";// 系统安装的软件列表
      oid = "1.3.6.1.2.1.2.1.0";// 网络接口的数目
      oid = ".1.3.6.1.2.1.2.2.1.2";// 网络接口信息描述
      oid = ".1.3.6.1.2.1.2.2.1.4";
      oid = ".1.3.6.1.2.1.2.2.1.3";
      oid = ".1.3.6.1.2.1.2.2.1.5";
      oid = ".1.3.6.1.2.1.25.3.3.1.2"; // CPU的当前负载，N个核就有N个负载
      oid = ".1.3.6.1.2.1.25.2.3.1.6";
//      oid = "";

      // 异步采集数据  
		snmpAsynWalk(ip, community, oid);
	}

}
