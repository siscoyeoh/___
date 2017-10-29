package yl.swing.c4;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
  
/** 
 * ����ѡ����������ָ�����ڵ���ʾ��ʽ 
 */  
public class DateChooserXXXXXX extends JPanel{
      
   private static final long serialVersionUID = 4529266044762990227L;
  
	private Date initDate;
	private Calendar now = Calendar.getInstance();
	private Calendar select;
	private JPanel monthPanel;// ����
	private JP1 jp1;
	private JP2 jp2;
	private Font font = new Font("����", Font.PLAIN, 12);
	private final LabelManager lm = new LabelManager();
	private JLabel showDate;// ,toSelect;
	private SimpleDateFormat sdf;
	private boolean isShow = false;
	private Popup pop;
      
    /** 
     * Creates a new instance of DateChooser 
     */  
    public DateChooserXXXXXX() {
        this(new Date());
    }
    public DateChooserXXXXXX(Date date){
       this(date, "yyyy��MM��dd��");
    }
    public DateChooserXXXXXX(String format){
        this(new Date(), format);
    }
    public DateChooserXXXXXX(Date date, String format){
        initDate=date;
        sdf=new SimpleDateFormat(format);
        select=Calendar.getInstance();
        select.setTime(initDate);
        initPanel();
        initLabel();
    }
      
    /** 
     * �Ƿ������û�ѡ�� 
     */  
    public void setEnabled(boolean b){
        super.setEnabled(b);
        showDate.setOpaque(false);
        showDate.setBackground(Color.blue);
        showDate.setEnabled(b);
    }
      
    /** 
     *�õ���ǰѡ�������� 
     */  
    public Date getDate(){
        return select.getTime();
    }
      
    //���ݳ�ʼ��������,��ʼ�����  
    private void initPanel(){
        monthPanel=new JPanel(new BorderLayout());
        monthPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JPanel up=new JPanel(new BorderLayout());
        up.add(jp1=new JP1(),BorderLayout.NORTH);
        monthPanel.add(jp2=new JP2(),BorderLayout.CENTER);
        monthPanel.add(up,BorderLayout.NORTH);
        this.addAncestorListener(new AncestorListener(){
            public void ancestorAdded(AncestorEvent event) {
                  
            }
            public void ancestorRemoved(AncestorEvent event) {
                  
            }
            //ֻҪ�������һ�ƶ�,���Ͼ���popup��ʧ  
            public void ancestorMoved(AncestorEvent event) {
                hidePanel();
            }
        });
    }
    //��ʼ����ǩ  
    private void initLabel(){
        showDate=new JLabel(sdf.format(initDate));
        showDate.setRequestFocusEnabled(true);
        showDate.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent me){
                showDate.requestFocusInWindow();
            }
        });
        this.setBackground(Color.WHITE);
        this.add(showDate,BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(90,25));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        showDate.addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent me){
                if(showDate.isEnabled()){
                    showDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    showDate.setForeground(Color.RED);
                }
            }
            public void mouseExited(MouseEvent me){
                if(showDate.isEnabled()){
                    showDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    showDate.setForeground(Color.BLACK);
                }
            }
            public void mousePressed(MouseEvent me){
                if(showDate.isEnabled()){
                    showDate.setForeground(Color.CYAN);
                    if(isShow){
                        hidePanel();
                    }else{
                        showPanel(showDate);
                    }
                }
            }
            public void mouseReleased(MouseEvent me){
                if(showDate.isEnabled()){
                    showDate.setForeground(Color.BLACK);
                }
            }
        });
        showDate.addFocusListener(new FocusListener(){
            public void focusLost(FocusEvent e){
                hidePanel();
            }
            public void focusGained(FocusEvent e){
                  
            }
        });
    }
    //�����µ�����ˢ��  
    private void refresh(){
        jp1.updateDate();
        jp2.updateDate();
        SwingUtilities.updateComponentTreeUI(this);
    }
    //�ύ����  
    private void commit(){
        System.out.println("ѡ�е������ǣ�"+sdf.format(select.getTime()));
        showDate.setText(sdf.format(select.getTime()));
        hidePanel();
    }
    //��������ѡ�����  
    private void hidePanel(){
        if(pop!=null){
            isShow=false;
            pop.hide();
            pop=null;
        }
    }
    //��ʾ����ѡ�����  
    private void showPanel(Component owner){
        if(pop!=null){
            pop.hide();
        }
        Point show=new Point(0,showDate.getHeight());
        SwingUtilities.convertPointToScreen(show,showDate);
        Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int x=show.x;
        int y=show.y;
        if(x<0){
            x=0;
        }
        if(x>size.width-295){
            x=size.width-295;
        }
        if(y<size.height-170){
        }else{
            y-=188;
        }
        pop=PopupFactory.getSharedInstance().getPopup(owner,monthPanel,x,y);
        pop.show();
        isShow=true;
    }
    /** 
     * ����������������ʾ�·ݵ����� 
     */  
    private class JP1 extends JPanel{
		private static final long serialVersionUID = 7680620945959743538L;
		JLabel yearleft,yearright,monthleft,monthright,center,centercontainer;
        public JP1(){
            super(new BorderLayout());
            this.setBackground(new Color(160,185,215));
            initJP1();
        }
        private void initJP1(){
            yearleft=new JLabel("  <<",JLabel.CENTER);
            yearleft.setToolTipText("��һ��");
            yearright=new JLabel(">>  ",JLabel.CENTER);
            yearright.setToolTipText("��һ��");
            yearleft.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));
            yearright.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));
              
            monthleft=new JLabel("  <", JLabel.RIGHT);
            monthleft.setToolTipText("��һ��");
            monthright=new JLabel(">  ", JLabel.LEFT);
            monthright.setToolTipText("��һ��");
            monthleft.setBorder(BorderFactory.createEmptyBorder(2,30,0,0));
            monthright.setBorder(BorderFactory.createEmptyBorder(2,0,0,30));
              
            centercontainer=new JLabel("", JLabel.CENTER);
            centercontainer.setLayout(new BorderLayout());
            center=new JLabel("", JLabel.CENTER);
  
            centercontainer.add(monthleft,BorderLayout.WEST);
            centercontainer.add(center,BorderLayout.CENTER);
            centercontainer.add(monthright,BorderLayout.EAST);
              
            this.add(yearleft,BorderLayout.WEST);
            this.add(centercontainer,BorderLayout.CENTER);
            this.add(yearright,BorderLayout.EAST);
            this.setPreferredSize(new Dimension(295,25));
              
            updateDate();
              
            yearleft.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent me){
                    yearleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearleft.setForeground(Color.RED);
                }
                public void mouseExited(MouseEvent me){
                    yearleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearleft.setForeground(Color.BLACK);
                }
                public void mousePressed(MouseEvent me){
                    select.add(Calendar.YEAR,-1);
                    yearleft.setForeground(Color.WHITE);
                    refresh();
                }
                public void mouseReleased(MouseEvent me){
                    yearleft.setForeground(Color.BLACK);
                }
            });
            yearright.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent me){
                    yearright.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    yearright.setForeground(Color.RED);
                }
                public void mouseExited(MouseEvent me){
                    yearright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    yearright.setForeground(Color.BLACK);
                }
                public void mousePressed(MouseEvent me){
                    select.add(Calendar.YEAR,1);
                    yearright.setForeground(Color.WHITE);
                    refresh();
                }
                public void mouseReleased(MouseEvent me){
                    yearright.setForeground(Color.BLACK);
                }
            });
            monthleft.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent me){
                    monthleft.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthleft.setForeground(Color.RED);
                }
                public void mouseExited(MouseEvent me){
                    monthleft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthleft.setForeground(Color.BLACK);
                }
                public void mousePressed(MouseEvent me){
                    select.add(Calendar.MONTH,-1);
                    monthleft.setForeground(Color.WHITE);
                    refresh();
                }
                public void mouseReleased(MouseEvent me){
                    monthleft.setForeground(Color.BLACK);
                }
            });
            monthright.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent me){
                    monthright.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    monthright.setForeground(Color.RED);
                }
                public void mouseExited(MouseEvent me){
                    monthright.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    monthright.setForeground(Color.BLACK);
                }
                public void mousePressed(MouseEvent me){
                    select.add(Calendar.MONTH,1);
                    monthright.setForeground(Color.WHITE);
                    refresh();
                }
                public void mouseReleased(MouseEvent me){
                    monthright.setForeground(Color.BLACK);
                }
            });
        }
        private void updateDate(){
            center.setText(select.get(Calendar.YEAR)+"��"+(select.get(Calendar.MONTH)+1)+"��");
        }
    }
    private class JP2 extends JPanel{
		private static final long serialVersionUID = 247119773199423885L;
		public JP2(){
            super(new GridLayout(6,7));
            this.setPreferredSize(new Dimension(295,100));
            initJP3();
        }
        private void initJP3(){
            updateDate();
        }
        public void updateDate(){
            this.removeAll();
            lm.clear();
            Date temp=select.getTime();
            Calendar select=Calendar.getInstance();
            select.setTime(temp);
            select.set(Calendar.DAY_OF_MONTH,1);
            int index=select.get(Calendar.DAY_OF_WEEK);
            int sum=(index==1?8:index);
            select.add(Calendar.DAY_OF_MONTH,0-sum);
            for(int i=0;i<42;i++){
                select.add(Calendar.DAY_OF_MONTH,1);
                lm.addLabel(new MyLabel(select.get(Calendar.YEAR),  
                        select.get(Calendar.MONTH),select.get(Calendar.DAY_OF_MONTH)));
            }
            for(MyLabel my:lm.getLabels()){
                this.add(my);
            }
            select.setTime(temp);
        }
    }
    private class MyLabel extends JLabel implements Comparator<MyLabel>,  
            MouseListener,MouseMotionListener{
		private static final long serialVersionUID = -8195067426444939156L;
		private int year,month,day;
        private boolean isSelected;
        public MyLabel(int year,int month,int day){
            super(""+day,JLabel.CENTER);
            this.year=year;
            this.day=day;
            this.month=month;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.setFont(font);
            if(month==select.get(Calendar.MONTH)){
                this.setForeground(Color.BLACK);
            }else{
                this.setForeground(Color.LIGHT_GRAY);
            }
            if(day==select.get(Calendar.DAY_OF_MONTH)){
                this.setBackground(new Color(160,185,215));
            }else{
                this.setBackground(Color.WHITE);
            }
        }
        public boolean getIsSelected(){
            return isSelected;
        }
        public void setSelected(boolean b,boolean isDrag){
            isSelected=b;
            if(b&&!isDrag){
                int temp=select.get(Calendar.MONTH);
                select.set(year,month,day);
                if(temp==month){
                    SwingUtilities.updateComponentTreeUI(jp2);
                }else{
                    refresh();
                }
            }
            this.repaint();
        }
        protected void paintComponent(Graphics g){
            if(day==select.get(Calendar.DAY_OF_MONTH)&&  
                    month==select.get(Calendar.MONTH)){
                //�����ǰ������ѡ������,�������ʾ  
                g.setColor(new Color(160,185,215));
                g.fillRect(0,0,getWidth(),getHeight());
            }
            if(year==now.get(Calendar.YEAR)&&  
                    month==now.get(Calendar.MONTH)&&  
                    day==now.get(Calendar.DAY_OF_MONTH)){
                //������ں͵�ǰ����һ��,���ú��  
                Graphics2D gd=(Graphics2D)g;
                gd.setColor(Color.RED);
                Polygon p=new Polygon();
                p.addPoint(0,0);
                p.addPoint(getWidth()-1,0);
                p.addPoint(getWidth()-1,getHeight()-1);
                p.addPoint(0,getHeight()-1);
                gd.drawPolygon(p);
            }
            if(isSelected){//�����ѡ���˾ͻ���һ�����߿����  
                Stroke s=new BasicStroke(1.0f,BasicStroke.CAP_SQUARE,  
                        BasicStroke.JOIN_BEVEL,1.0f,new float[]{2.0f,2.0f},1.0f);
                Graphics2D gd=(Graphics2D)g;
                gd.setStroke(s);
                gd.setColor(Color.BLACK);
                Polygon p=new Polygon();
                p.addPoint(0,0);
                p.addPoint(getWidth()-1,0);
                p.addPoint(getWidth()-1,getHeight()-1);
                p.addPoint(0,getHeight()-1);
                gd.drawPolygon(p);
            }
            super.paintComponent(g);
        }
        public boolean contains(Point p){
            return this.getBounds().contains(p);
        }
        private void update(){
            repaint();
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
            isSelected=true;
            update();
        }
        public void mouseReleased(MouseEvent e) {
            Point p=SwingUtilities.convertPoint(this,e.getPoint(),jp2);
            lm.setSelect(p,false);
            commit();
        }
        public void mouseEntered(MouseEvent e) {
        }
          
        public void mouseExited(MouseEvent e) {
        }
        public void mouseDragged(MouseEvent e) {
            Point p=SwingUtilities.convertPoint(this,e.getPoint(),jp2);
            lm.setSelect(p,true);
        }
        public void mouseMoved(MouseEvent e) {
        }
        public int compare(MyLabel o1, MyLabel o2) {
            Calendar c1=Calendar.getInstance();
            c1.set(o1.year,o2.month,o1.day);
            Calendar c2=Calendar.getInstance();
            c2.set(o2.year,o2.month,o2.day);
            return c1.compareTo(c2);
        }
    }
    private class LabelManager{
        private List<MyLabel> list;
        public LabelManager(){
            list=new ArrayList<MyLabel>();
        }
        public List<MyLabel> getLabels(){
            return list;
        }
        public void addLabel(MyLabel my){
            list.add(my);
        }
        public void clear(){
            list.clear();
        }
        public void setSelect(Point p, boolean b){
            //������϶�,��Ҫ�Ż�һ��,�����Ч��  
            if(b){
                //��ʾ�Ƿ��ܷ���,���ñȽ������еı�ǩ,�ܷ��صı�־���ǰ���һ����ǩ��  
                //��Ҫ��ʾ�ı�ǩ�ҵ��˾Ϳ�����  
                boolean findPrevious=false,findNext=false;
                for(MyLabel m:list){
                    if(m.contains(p)){
                        findNext=true;
                        if(m.getIsSelected()){
                            findPrevious=true;
                        }else{
                            m.setSelected(true,b);
                        }
                    }else if(m.getIsSelected()){
                        findPrevious=true;
                        m.setSelected(false,b);
                    }
                    if(findPrevious&&findNext){
                        return;
                    }
                }
            }else{
                MyLabel temp=null;
                for(MyLabel m:list){
                    if(m.contains(p)){
                        temp=m;
                    }else if(m.getIsSelected()){
                        m.setSelected(false,b);
                    }
                }
                if(temp!=null){
                    temp.setSelected(true,b);
                }
            }
        }
          
    }
}