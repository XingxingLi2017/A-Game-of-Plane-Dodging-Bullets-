import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;


public class Game extends JFrame 
{
	public static void main(String[] args) {
		Game game = new Game();
		//������Ϸ���߳�
		game.start();
		game.reset();
		game.gogo();
		
	}
//******************���캯��*************************//
	public Game() 
	{
		super();
		initialize();
	}
//******************���캯��*************************//

//************************��ʼ��Frame����*******************//
	private void initialize() 
	{
		this.setSize(700, 700);
		this.addWindowListener
		(new java.awt.event.WindowAdapter() 
		{
			public void windowClosing(java.awt.event.WindowEvent e) 
			{
					System.exit(1);
			}
		});
		
		this.setResizable(false);
		//������Ϸ���jPanel
		this.setContentPane(getJPanel());
		this.setTitle("˭�ܳŹ�30�룿");
		this.setVisible(true);
	}
//************************��ʼ��Frame����	end*******************//
	
//****************��ʼ��Ϸ����****************//
	public void start() 
	{
		int chushihua = 0;
		//�������ӵ�
		while (chushihua < zidanshu)
		{
			JButton jb = new JButton();
			jb.setBounds(new Rectangle(-50, -50, zhidanlength, zhidanlength));
			jb.setIcon(new ImageIcon(fileLoc3));
			jb.setBackground(new Color(1, 1, 1));
			jb.setContentAreaFilled(false);
			jb.setBorderPainted(false);
			jb.setFocusPainted(false);
			
			Threads ths = new Threads(jb);
			Thread th = new Thread(ths);
			buttonal.add(jb);
			//�����ӵ�����JButton�ؼ���ʾ�ӵ�������buttonal��
			threadal.add(th);
			//����ÿһ���ӵ���Ӧ�Ŀ����̣߳�����threadal��
			chushihua++;
		}
		
		//��������ƶ���������߳�
		Game.Move move = new Move();
		Thread tm = new Thread(move);
		tm.start();
	}
//*******************��ʼ����	end*****************//	
	
	
//****************������Ϸ����**************//
	public void reset() 
	{
		fanhui.setVisible(false);
		//�������Ұ�������
		kup = false;
		kdown = false;
		kleft = false;
		kright = false;
	
		int chushihua = 0;
		while (chushihua < zidanshu) 
		{
			//�ƶ�buttonal�е��ӵ���ָ��λ�ò��ҵ�����С
				((JButton) buttonal.get(chushihua)).setBounds(new Rectangle(-50,
						-50, zhidanlength, zhidanlength));
				chushihua++;
		}
		//������Ϸ��ʼ��־����
		if(startpanel == true)
		gamexunhuan = true;
		//���÷ɻ�λ�ú�����
		jButton.setIcon(new ImageIcon(fileLoc));
		jButton.setLocation(320, 320);
		//�÷ɻ�����ӵ�жԼ��̵ļ���Ȩ������ֹ�����ӵ��İ�ť�Լ��̼�����������Ҳ���
		jButton.requestFocus();
		//������ҵ�ǰλ��
		p = jButton.getLocation();
		x=p.getX();
		y=p.getY();
		//���ÿ�ʼʱ��
		firsttime=new Date().getTime();
	}
//****************������Ϸ����	end**************//
	
//******************�����ӵ������̺߳���***************//
		public void gogo() {
			int chushihua = 0;
			while (chushihua < zidanshu) 
			{
				//���ο���ÿ���ӵ���Ӧ�Ŀ����߳�
				((Thread) threadal.get(chushihua)).start();
				chushihua++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
				
			}
		}
//******************�����ӵ������̺߳���	end***************//
	
//****************���ɿ�ʼ��Ϸ��ť*****************//
	private	JButton getKaiShi()
	{
		if(kaishi == null)
		{
			kaishi = new JButton();
			kaishi.setBounds(new Rectangle(300,300,100,50));
			kaishi.setText("��ʼ��Ϸ");
			kaishi.setVisible(true);
			kaishi.addActionListener
			(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					//���ؿ�ʼ�����ǩ
					kaishi.setVisible(false);
					gamename.setVisible(false);
					jieshao.setVisible(false);
					zuidajilu.setVisible(false);
					//��ʾ�ɻ�
					jButton.setVisible(true);
					//�л�����Ϸ�����־
					startpanel = true;
					//������Ϸ
					reset();
				}
			}
					
			);
			
		}
		return kaishi;
	}
	
//****************���ɿ�ʼ��Ϸ��ť	end*****************//
	
//************************������Ϸ���ܰ�ť***************************//
		private	JButton getJieShao()
		{
			if(jieshao == null)
			{
				jieshao = new JButton();
				jieshao.setBounds(new Rectangle(300,400,100,50));
				jieshao.setText("��Ϸ˵��");
				jieshao.setVisible(true);
				jieshao.addActionListener
				(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						//������Ϸ���ܶԻ���
						String s1 = "ʹ�á����������ƶ��ɻ�����ӵ��������ܵĴ���ʱ�䣡";
						JOptionPane.showMessageDialog(null, s1,"��Ϸ˵��",JOptionPane.PLAIN_MESSAGE);
					
					}
				}
						
				);
				
			}
			return jieshao;
		}
		
//*************************������Ϸ���ܰ�ť	end****************************//
		
//*******************��������¼��ť********************//
		private JButton getZuiDaJiLu()
		{
			if(zuidajilu == null)
			{
				zuidajilu = new JButton();
				zuidajilu.setBounds(new Rectangle(300,500,100,50));
				zuidajilu.setText("����¼");
				zuidajilu.setVisible(true);
				zuidajilu.addActionListener
				(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						int max;
						//�õ�����¼
						max = getBestRecord();
						Integer g = new Integer(max);
						//��������¼�Ի���
						String s1 = "��Ϸ����¼�� ��"+g.toString()+"�룡";
						JOptionPane.showMessageDialog(null, s1,"��Ϸ����¼",JOptionPane.PLAIN_MESSAGE);
											
					}
				}
						
				);
				
			}
			return zuidajilu;
		}
//*******************��������¼��ť		end********************//	
		
//****************���ɷ��������水ť*****************//
	private	JButton getFanHui()
	{
		if(fanhui == null)
		{
			fanhui = new JButton();
			fanhui.setBounds(new Rectangle(150, 361, 164, 51));
			fanhui.setText("����������");
			fanhui.setVisible(false);
			fanhui.addActionListener
			(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					//������Ϸ�����������
					chongxinkaishi.setVisible(false);
					jLabel.setVisible(false);
					fanhui.setVisible(false);
					//���طɻ�
					jButton.setVisible(false);
					//��ʾ���������
					kaishi.setVisible(true);
					gamename.setVisible(true);
					jieshao.setVisible(true);
					zuidajilu.setVisible(true);
					//�л���Ϸ�����־��־
					startpanel = false;
					//������Ϸ
					reset();
				}
			}
					
			);
			
		}
		return fanhui;
	}
//****************���ɷ��������水ť	end*****************//	
	
//**********************�������¿�ʼ��ť**********************//	
 	private JButton getChongXinKaiShi() 
	{
		//�������¿�ʼ��ť
		if (chongxinkaishi == null) 
		{
			chongxinkaishi = new JButton();
			chongxinkaishi.setBounds(new Rectangle(478, 361, 164, 51));
			chongxinkaishi.setText("���¿�ʼ");
			chongxinkaishi.setVisible(false);
			chongxinkaishi.addActionListener
			//���¿�ʼ��ť�¼����������¿�ʼ��ť��jLabel��������Ϸ
			(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//������Ϸ��������İ�ť
					chongxinkaishi.setVisible(false);
					jLabel.setVisible(false);
					fanhui.setVisible(false);
					try 
					{
							Thread.sleep(500);
					} 
					catch (InterruptedException e1) 
					{
						// TODO �Զ����� catch ��
						e1.printStackTrace();
					}
						reset();
	
				}
			});
		}
		return chongxinkaishi;
	}
//**********************�������¿�ʼ��ť	end**************//	
 	
//*******************�����������*************************//
 		private JButton getJButton() 
 		{
 			if (jButton == null) {
 				jButton = new JButton();
 				jButton.setBounds(new Rectangle(320, 320, jButtonlength, jButtonlength));
 				jButton.setBackground(new Color(1, 1, 1));
 				jButton.setContentAreaFilled(false);
 				jButton.setBorderPainted(false);
 				jButton.setFocusPainted(false);
 				jButton.setVisible(false);
 				p = jButton.getLocation();
 				x = p.getX();
 				y = p.getY();
 				jButton.setIcon(new ImageIcon(fileLoc));
 				//��button��Ӵ�������button��Ӧ�����ϵġ���������
 				jButton.addKeyListener
 				(new java.awt.event.KeyAdapter() 
 				{
 					public void keyReleased(java.awt.event.KeyEvent e) {
 						if(e.getKeyCode()==10){
 							if(!gamexunhuan){
 								chongxinkaishi.setVisible(false);
 								jLabel.setVisible(false);
 								reset();
 							}
 						}
 						if (e.getKeyCode() == 37) {
 							kleft = false;
 						}
 						if (e.getKeyCode() == 38) {
 							kup = false;
 						}
 						if (e.getKeyCode() == 39) {
 							kright = false;
 						}
 						if (e.getKeyCode() == 40) {
 							kdown = false;
 						}
 					}

 					public void keyPressed(java.awt.event.KeyEvent e) {
 						if (e.getKeyCode() == 37) {
 							kleft = true;

 						}
 						if (e.getKeyCode() == 38) {
 							kup = true;

 						}
 						if (e.getKeyCode() == 39) {
 							kright = true;

 						}
 						if (e.getKeyCode() == 40) {
 							kdown = true;

 						}
 					}
 				});
 			}
 			return jButton;
 		}
//*******************�����������	end********************//
 	
	
//**************************������Ϸ��庯��*******************//	
	private JPanel getJPanel() 
	{
		if (jPanel == null) 
		{
			//������Ϸ���
			jPanel = new newPanel();
			jPanel.setLayout(null);//���Բ���
			jPanel.setForeground(new Color(1, 1, 1));
			jPanel.setBackground(new Color(1, 1, 1));
			jPanel.setVisible(true);
			//������Ϸ����ʱ��������ʾ�ı�ǩ
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(42, -33, 595, 308));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 24));
			jLabel.setForeground(Color.red);
			jLabel.setEnabled(true);
			jLabel.setVisible(false);
			jPanel.add(jLabel, null);
			//������Ϸ��ʼʱ��������ʾ�ı�ǩ
			gamename = new JLabel();
			gamename.setBounds(new Rectangle(220, -33, 595, 308));
			gamename.setFont(new Font("Dialog", Font.BOLD, 40));
			gamename.setForeground(Color.red);
			gamename.setEnabled(true);
			gamename.setVisible(true);
			gamename.setText("˭�ܼ��30�룿");
			jPanel.add(gamename,null);
			//���ɿ�ʼ��Ϸ��ť
			jPanel.add(getKaiShi(),null);
			//������Ϸ���ܰ�ť
			jPanel.add(getJieShao(),null);
			//��������¼��ť
			jPanel.add(getZuiDaJiLu(),null);
			//������ҿ��Ƶ�����
			jPanel.add(getJButton(), null);
			//�������¿�ʼ��ť
			jPanel.add(getChongXinKaiShi(), null);
			//���ɷ��������水ť
			jPanel.add(getFanHui(),null);
			
		}
			return jPanel;
	}
//**************************������Ϸ��庯��	end*******************//
		

//******************��ȡ����¼******************//
	public int getBestRecord()
	{	
		String num;
		int max = 0;
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("MyGameIcons/��¼.txt"));
			
			num = in.readLine();
			max = Integer.parseInt(num);
			in.close();
		}
		catch(IOException e) { 
            e.printStackTrace(); 
        } 
		return max;
	}

//******************��ȡ����¼	end******************//
	
//******************������¼д���ļ�*********************//
	public void writeBestRecord(int x)
	{
		Integer num;
		try
		{
			num = new Integer(x);
			BufferedWriter out = 
	                new BufferedWriter(new FileWriter("MyGameIcons/��¼.txt"));
			out.write(num.toString());
			out.close();
		}
		catch(IOException e) { 
            e.printStackTrace(); 
        } 
		return ;
	}
	
//******************������¼д���ļ�	end*********************//		
	
//**********************����ƶ������̺߳���******************//	
		class Move implements Runnable 
		{
			public void run() 
			{
				while(true)
				{
					while (gamexunhuan) 
					{
						//�õ����ﵱǰλ��
						p = jButton.getLocation();
						//��ס���ϼ�
						if (kup) 
						{
							//��ס�����
							if (kleft) {
								x = p.getX();
								y = p.getY();
								if (x > 0 && y > 0) {
									//���������ƶ�
									jButton.setLocation((int) x - step, (int) y
											- step);
								}
							} 
							//����
							else if (kright)
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700 && y > 0) 
								{
									//�������ƶ�
									jButton.setLocation((int) x + step, (int) y
											- step);
								}
							} 
							//ֻ����
							else 
							{
								x = p.getX();
								y = p.getY();
								if (y > 0) {
									jButton.setLocation((int) x, (int) y - step);
								}
							}
						}
						//��ס���¼�
						if (kdown) 
						{
							if (kleft) 
							{
								x = p.getX();
								y = p.getY();
								if (y + 60 < 700 && x > 0) {
									jButton.setLocation((int) x - step, (int) y
											+ step);
								}
							} 
							else if (kright) 
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700 && y + 60 < 700) 
								{
									jButton.setLocation((int) x + step, (int) y
											+ step);
								}
							} 
							else 
							{
								x = p.getX();
								y = p.getY();
								if (y + 60 < 700) {
									jButton.setLocation((int) x, (int) y + step);
								}
							}
						}
						if (kleft) 
						{
							if (kup) 
							{
								x = p.getX();
								y = p.getY();
								if (x > 0 && y > 0) {
									jButton.setLocation((int) x - step, (int) y
											- step);
								}
							} 
							else if (kdown) 
							{
								x = p.getX();
								y = p.getY();
								if (y + 60 < 700 && x > 0) {
									jButton.setLocation((int) x - step, (int) y
											+ step);
								}
							} 
							else 
							{
								x = p.getX();
								y = p.getY();
								if (x > 0) {
									jButton.setLocation((int) x - step, (int) y);
								}
							}
						}
						if (kright) 
						{
							if (kup) 
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700 && y > 0) {
									jButton.setLocation((int) x + step, (int) y
											- step);
								}
							} 
							else if (kdown) 
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700 && y + 60 < 700) {
									jButton.setLocation((int) x + step, (int) y
											+ step);
								}
							} 
							else 
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700) {
									jButton.setLocation((int) x + step, (int) y);
								}
							}
						}
						try 
						{
							//ÿ��0.01���ƶ�һ��
							Thread.sleep(10);
						} 
						catch (InterruptedException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
					}
					try 
					{
						//ÿ��0.05��鿴gamexunhuanֵ�ж���Ϸ�Ƿ�ʼ
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
				}
			}
		}
//**********************����ƶ������̺߳��� 	end******************//	

	
//*******************�ӵ������̴߳�����******************//
	class Threads implements Runnable 
	{
		//�ڿ�ʼ�����л������ӵ����ҽ�һ���ӵ����͹�����jb��Ҫ������ӵ�
		public Threads(JButton jjb) {
			jb = jjb;
		}
		
		JButton jb = null;
		//first��ʾ���߳̿��е��ӵ�button�Ƿ���ӵ�panel����ʾ����,��ʼ��ʱ���Ȼ��һ���ӵ�
		private boolean first = true;

		public void run() {
			while (true) {
				go();
			}
		}

		public void go() {
			//zy,zxΪ�ӵ�����λ�ã��������
			//zzx��zzyΪ�ӵ�������֮��Ĳ������ӵ�ÿ��һ����
			int zzx = 0;
			int zzy = 0;
			int zx = 0;
			int zy = 0;
			while (true) {
				//��gamexunhuanȫ�ֱ��������������̣߳�true��ʾ��Ϸ���У�false��ʾ��Ϸ����
				if(gamexunhuan)
				{
					int fangxiang = (int) (Math.random() * 4 + 1);
					// �ĸ�if�漴���ĸ��߷����ӵ�
					if (fangxiang == 1) {
						//����ߵı߷���
						zx = 0;
						zy = (int) (Math.random() * 701);
					}
					if (fangxiang == 2) {
						//���ϱߵı߷���
						zx = (int) (Math.random() * 701);
						zy = 0;
					}
					if (fangxiang == 3) {
						//���ұߵı߷���
						zx = 700;
						zy = (int) (Math.random() * 701);
					}
					if (fangxiang == 4) {
						//���±ߵı߷���
						zx = (int) (Math.random() * 701);
						zy = 700;
					}
					// ��ʼ���ӵ�����firstΪfalse��ʱ���ʾjb�Ѿ���ӵ������
					// ֻ��Ҫ����λ�ü��ɲ��������
					if (first) {
						jPanel.add(jb, null);
						first = false;
					}
					jb.setBounds(new Rectangle(zx, zy, 10, 10));
					// �����ӵ�������֮��Ĳ���
					//x,yΪ��ҵ�ǰλ��
					//��ҳ���Ϊ30���ӵ�����Ϊ10
					zzx = (int) ((x  - zx) / 50);
					zzy = (int) ((y - zy) / 50);
					}
					while (gamexunhuan) 
					{
						try 
						{
							zx += zzx;
							zy += zzy;
							jb.setLocation(zx, zy);
							//�ж�����ײ����
							if (zx + zhidanlength/2 > x 
								& zx + zhidanlength/2 < x + jButtonlength 
								& zy + zhidanlength/2 > y
								& zy + zhidanlength/2 < y + jButtonlength) 
							{
								//��ײ���ӵ���������ﰴť�ϵ�ͼ��仯
								jButton.setIcon(new ImageIcon(fileLoc1));
								//ͨ��gamexunhuan�������߳�ȫ��ֹͣ
								gamexunhuan = false;
								//�ӵ�buttonҪ������ӵ������
								first = true;
								//��ʾ��Ϸ�����������
								chongxinkaishi.setVisible(true);
								jLabel.setVisible(true);
								fanhui.setVisible(true);
								//������Ϸʱ��
								lasttime = new Date().getTime();
								Date gametime = new Date(lasttime-firsttime);
								int min =0; 
								int sec =0;
								int usetime;
								int jilu;
								min = gametime.getMinutes();
								sec = gametime.getSeconds();
								String endtime = "";
								if(min!=0){
										endtime=min + "��  " + sec + "��";
								}else{
									endtime=sec + "��";
								}
								//��ǩ����Ϊ����ʱ��
								jLabel.setText("                          GAME OVER!!! \n��ʱ��" + endtime);
								//�ж��ǲ�������¼
								usetime = min*60+sec;
								jilu = getBestRecord();
								if(usetime > jilu)
								{
									Integer a;
									a = new Integer(usetime);
									writeBestRecord(usetime);
									String s1 = "��ϲ���������¼����ĳɼ��� ��"+a.toString()+" ��";
									JOptionPane.showMessageDialog(null, s1,"��Ϸ����¼",JOptionPane.PLAIN_MESSAGE);	
								}
								break;
							}
							// ��������ֹͣѭ��,�ӵ������������λ�÷���
							if (zx > 700 | zy > 700 | zx < 0 | zy < 0) {
								break;
							}
							//�ӵ�ÿ���0.06����һ��
							Thread.sleep(60);
						} 
						catch (InterruptedException e) 
						{
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
					}
					try {
						//���Ϊ�ӵ������߽��������ʾ0.05��������ӵ�λ��
						//���Ϊ��Ϸ��������ʾÿ��0.05������Ϸ������־һ��
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
		}
	}
//*******************�ӵ������̴߳�����	end******************//
	
//***********************������Ϸ������************************//
 		class newPanel extends JPanel
		{
			public newPanel()
			{
				
			}
			//���������ӱ���
			public void paintComponent(Graphics g)
			{
				int x = 0,y = 0;
				ImageIcon beijing = new ImageIcon(fileLoc2);
				g.drawImage(beijing.getImage(), x, y,getSize().width,getSize().height,this);
//				while(true)
//				{
//					g.drawImage(beijing.getImage(),x,y,this);
//					if(x>getSize().width && y > getSize().height)
//						break;
//					//���´����ʾ�����ڴ���ͼƬʱ��֤ͼƬ��������
//					if(x>getSize().width)
//					{
//						x = 0;
//						y= y+beijing.getIconHeight();
//					}
//					else
//						x = x+beijing.getIconWidth();
//				}
				
			}
		}

//***********************������Ϸ���	end************************//
	
//*************************************ȫ�ֱ���***********************************//
		//��Ϸ��ʼʱ��
		private long firsttime;
		//��Ϸ����ʱ��
		private long lasttime;
		//��Ϸ�����
		private newPanel jPanel = null;
		//jbutton��ʾ��Ҳٿص�����ð�ť�ؼ���ʵ��
		private JButton jButton = null;
		//����߳����ӵ��߳�
		private	int jButtonlength = 50;
		private int zhidanlength = 10;
		//kup,kdown,kleft,kright��ʾ�û�����
		//������ΪTRUEʱ���ʾ�û��ֱ�ס�˼�
		//������Ϊfalseʱ���ʾ�û��ɿ��˼�
		private boolean kup ;
		private boolean kdown ;
		private boolean kleft ;
		private boolean kright ;
		// ������ҵ����߲�������ֵԽ���ƶ��ٶ�Խ��
		private int step = 5;
		//������¼��ҵ�ǰλ��
		Point p; 
		double x = 0.0;
		double y = 0.0;
		// �������ӵ��ĸ���
		int zidanshu = 20;
		// ��ʾ��Ϸ�Ƿ�������ֶΣ�Ϊtrue��ʾ��Ϸ������Ϊfalse��ʾ�����������Ϸ����
		private boolean gamexunhuan = false;
		//��ʾ��ǰ��������Ϸ���滹�ǿ�ʼ����
		//true��ʾ��Ϸ���棬��ʱ��reset������Ϸ�Ὣgamexunhuan����Ϊtrue����Ϸ���¿�ʼ
		//false��ʾ��ʼ���棬��ʱ��reset������Ϸ�Ὣ�����gamexunhuan����Ϊtrue���ص�������
		private boolean startpanel = false;
		//��Ϸ��ʼ������ʾ�Ŀؼ�
		private JLabel gamename = null;//��Ϸ����
		private JButton kaishi = null;//��ʼ��Ϸ
		private JButton jieshao = null;//��Ϸ���ܰ�ť
		private JButton zuidajilu = null;//����¼��ť
		//��Ϸ������������ʾ��Ϸ����Ŀؼ�
		private JLabel jLabel = null;//��ҳɼ�
		private JButton chongxinkaishi = null;//���¿�ʼ
		private JButton fanhui = null;//����������
		//��������ӵ���ÿ���ӵ���Ӧ�Ŀ����̵߳��ֶ�
		private ArrayList buttonal = new ArrayList();
		private ArrayList threadal = new ArrayList();
		//�����ŵ���ҿ��������ϵ�ͼ�������
		String fileLoc = "MyGameIcons/�ɻ�.gif";
		String fileLoc1 = "MyGameIcons/����.gif";
		String fileLoc2 = "MyGameIcons/����.jpg";
		String fileLoc3 = "MyGameIcons/�ӵ�.gif";
		
//********************************ȫ�ֱ���	end***************************************//
} 