package yl.snmp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
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
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;


import yl.CM;



/**
 * 探测agent并初始化agent的硬件信息
 * 
 * @Author Yang Lin
 * @Date 2017年9月30日
 * @Time 上午10:06:02
 */
public class DetectAgent2 {
	

	
//    public int version = SnmpConstants.version2c;
//    public String protocol = "udp";
//    public String ip;
//    public int port = 161;
//    public String community = "public";
//    public long timeout = 1 * 500L;
//    public int retry = 3;

	/**
	 * 创建对象communityTarget
	 * @param ip
	 * @param community
	 * @return
	 * @Author Yang Lin
	 * @Date 2017年9月20日
	 * @Time 下午1:18:27
	 */
    public static CommunityTarget createTarget(Agent agent) {
    	String targetIP = agent.getIp();
    	String protocol = agent.getProtocol();
    	int port = agent.getPort();
    	String community = agent.getCommunity();
    	int version = agent.getVersion();
    	long timeout = 500L;
    	int retries = 5;
    	try {
			if (InetAddress.getLocalHost().getHostAddress().equals(agent.getIp())) {
				targetIP = "127.0.0.1";
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Address address = GenericAddress.parse(protocol + ":" + targetIP  
                + "/" + port);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(version);
        target.setTimeout(timeout);// milliseconds  
        target.setRetries(retries);
        return target;
    }
    
    /** 同步获取信息 */  
    public static String GetSync(Agent agent, String oid, int requestID) {
  
    	String oidInfo = "";
    	CommunityTarget target = createTarget(agent);
    	Snmp snmp = null;
        try {
            PDU pdu = new PDU();
            // pdu.add(new VariableBinding(new OID(new int[]  
            // {1,3,6,1,2,1,1,2})));
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setRequestID(new Integer32(requestID));
  
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            System.out.println("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();
  
            if (response == null) {
                System.out.println("response is null, request time out");
            }else {
  
                // Vector<VariableBinding> vbVect =  
                // response.getVariableBindings();
                // System.out.println("vb size:" + vbVect.size());
                // if (vbVect.size() == 0) {
                // System.out.println("response vb size is 0 ");
                // }else {
                // VariableBinding vb = vbVect.firstElement();
                // System.out.println(vb.getOid() + " = " + vb.getVariable());
                // }
  
                System.out.println("response pdu size is " + response.size());
                for (int i = 0;i < response.size();i++) {
                    VariableBinding vb = response.get(i);
                    System.out.println("请求ID:" + response.getRequestID());
                    System.out.println(vb.getOid() + " = " + vb.getVariable());
                }
                if (response.size() > 0) {
                	oidInfo = response.get(0).getVariable().toString();
                }
  
            }
            System.out.println("SNMP GET one OID value finished !");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Get Exception:" + e);
        }finally {
            if (snmp != null) {
                try {
                    snmp.close();
                }catch (IOException ex1) {
                    snmp = null;
                }
            }
  
        }
        return oidInfo;
    }
    
    /**
     * 通用walk轮询
     * @param agent
     * @param requestID
     * @return
     * @Author Yang Lin
     * @Date 2017年10月9日
     * @Time 下午5:32:26
     */
    public static List<Object> WalkAsynCommon(Agent agent, String oid, int requestID) {
    	final List<Object> objs = new ArrayList<Object>();
        final CommunityTarget target = createTarget(agent);
        Snmp snmp = null;
        try {
//            System.out.println("----> demo start <----");
  
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
  
            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));
            pdu.setRequestID(new Integer32(requestID));
  
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
                            System.out.println("response.getRequestID():" + response.getRequestID());
                            VariableBinding vb = response.get(0);
  
                            boolean finished = checkWalkFinished(targetOID,  
                                    pdu, vb);
                            if (!finished) {
                                System.out.println(vb.getOid() + " = "  
                                        + vb.getVariable());
                                
                                objs.add(vb.getVariable());
                                
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                Object myHandler = new String("pi3.1415926pi");
                                ((Snmp) event.getSource()).getNext(pdu, target,  
                                		myHandler, this);
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
            System.out.println("pdu sent, waiting for response...");
  
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
            snmp.close();
  
            System.out.println("----> demo end <----");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Asyn Walk Exception:" + e);
        }
        
        return objs;
    }
    
//    /** 异步获取表格 */  
    /**
     * 轮询, 获得"磁盘编号", oid=".1.3.6.1.2.1.25.2.3.1.2", hrStorageType
     * @param agent
     * @param oid
     * @param requestID
     * @return
     * @Author Yang Lin
     * @Date 2017年10月9日
     * @Time 下午4:57:55
     */
    public static List<Integer> WalkAsynStorageType(Agent agent, int requestID) {
    	String oid = ".1.3.6.1.2.1.25.2.3.1.1";
    	final List<Integer> storageIndexes = new ArrayList<Integer>();
        final CommunityTarget target = createTarget(agent);
        Snmp snmp = null;
        try {
//            System.out.println("----> demo start <----");
  
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
  
            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));
            pdu.setRequestID(new Integer32(requestID));
  
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
                            System.out.println("response.getRequestID():" + response.getRequestID());
                            VariableBinding vb = response.get(0);
  
                            boolean finished = checkWalkFinished(targetOID,  
                                    pdu, vb);
                            if (!finished) {
                                System.out.println(vb.getOid() + " = "  
                                        + vb.getVariable());
                                
//                                storageIndexes.add(vb.getVariable().toInt());
                                // 解析出磁盘编号
                                Integer index = Integer.parseInt(vb.getVariable().toString());
                                // 把磁盘编号添加到队列
                                storageIndexes.add(index);
                                
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                Object myHandler = new String("pi3.1415926pi");
                                ((Snmp) event.getSource()).getNext(pdu, target,  
                                		myHandler, this);
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
            System.out.println("pdu sent, waiting for response...");
  
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
            snmp.close();
  
            System.out.println("----> demo end <----");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Asyn Walk Exception:" + e);
        }
        
        return storageIndexes;
    }

	public static Integer WalkAsynCPUCoreCount(Agent agent,
			int requestID) {
		String oid = ".1.3.6.1.2.1.25.3.3.1.2";
    	final List<Integer> storageIndexes = new ArrayList<Integer>();
        final CommunityTarget target = createTarget(agent);
        Snmp snmp = null;
        try {
//            System.out.println("----> demo start <----");
  
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
  
            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));
            pdu.setRequestID(new Integer32(requestID));
  
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
                            System.out.println("response.getRequestID():" + response.getRequestID());
                            VariableBinding vb = response.get(0);
  
                            boolean finished = checkWalkFinished(targetOID,  
                                    pdu, vb);
                            if (!finished) {
                                System.out.println(vb.getOid() + " = "  
                                        + vb.getVariable());

                              // 解析出磁盘编号
                              Integer index = Integer.parseInt(vb.getVariable().toString());
                              // 把磁盘编号添加到队列
                              storageIndexes.add(index);
                                
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                Object myHandler = new String("pi3.1415926pi");
                                ((Snmp) event.getSource()).getNext(pdu, target,  
                                		myHandler, this);
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
            System.out.println("pdu sent, waiting for response...");
  
            boolean wait = latch.await(30, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
            snmp.close();
  
            System.out.println("----> demo end <----");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP Asyn Walk Exception:" + e);
        }
		return storageIndexes.size();
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
	
    
	
	// ------------------------------
	// ------------------------------ telnet读写
	// ------------------------------
	
	
    
//    /**
//     * 写入命令方法
//     * @param cmd
//     * @param os
//     */
//    public static void write(String cmd, OutputStream os){
//        try {
//            cmd = cmd + "\n";
//            os.write(cmd.getBytes());
//            os.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//    }
//    
//    /**
//     * 读到指定位置,不在向下读
//     * @param endFlag
//     * @param in
//     * @return
//     */
//    public static String read(String endFlag, InputStream in) {
//        
//        InputStreamReader isr = new InputStreamReader(in);
//        
//        char[] charBytes = new char[1024];
//        int n = 0;
//        boolean flag = false;
//        String str = "";
//        try {
//            while((n = isr.read(charBytes)) != -1){
//                for(int i=0; i< n; i++){
//                    char c = (char)charBytes[i];
//                    str += c;
//                    //当拼接的字符串以指定的字符串结尾时,不在继续读
//                    if(str.endsWith(endFlag)){
//                        flag = true;
//                        break;
//                    }
//                }
//                if(flag){
//                    break;
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        
//        return str;
//    }
    
    public static void main(String[]args) {
    	Agent agent = new Agent();
//		private String ip;
//		private String protocol;
//		private int port;
//		private String community;
//		private int version;
		String ip = "127.0.0.1";
//		ip = "192.168.235.128";
		ip = "192.168.8.1";
//		ip = "192.168.8.115";
		agent.setIp(ip);
		agent.setProtocol("udp");
		agent.setPort(161);
		agent.setCommunity("public");
		agent.setVersion(SnmpConstants.version2c);
//    	List<Integer> indexes = WalkAsynStorageType(agent, 88888);
//    	System.out.println(CM.List2String(indexes));
		// 探测CPU核数
		Integer cpu_core_count = DetectAgent2.WalkAsynCPUCoreCount(agent, 8888);
		System.out.println("cpu_core_count:" + cpu_core_count);
		
    }
	
}
