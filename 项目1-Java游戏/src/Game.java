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
		//开启游戏子线程
		game.start();
		game.reset();
		game.gogo();
		
	}
//******************构造函数*************************//
	public Game() 
	{
		super();
		initialize();
	}
//******************构造函数*************************//

//************************初始化Frame函数*******************//
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
		//生成游戏面板jPanel
		this.setContentPane(getJPanel());
		this.setTitle("谁能撑过30秒？");
		this.setVisible(true);
	}
//************************初始化Frame函数	end*******************//
	
//****************开始游戏函数****************//
	public void start() 
	{
		int chushihua = 0;
		//先生成子弹
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
			//生成子弹，用JButton控件表示子弹，存入buttonal中
			threadal.add(th);
			//生成每一个子弹对应的控制线程，加入threadal中
			chushihua++;
		}
		
		//启动玩家移动人物的子线程
		Game.Move move = new Move();
		Thread tm = new Thread(move);
		tm.start();
	}
//*******************开始函数	end*****************//	
	
	
//****************重置游戏函数**************//
	public void reset() 
	{
		fanhui.setVisible(false);
		//上下左右按键重置
		kup = false;
		kdown = false;
		kleft = false;
		kright = false;
	
		int chushihua = 0;
		while (chushihua < zidanshu) 
		{
			//移动buttonal中的子弹到指定位置并且调整大小
				((JButton) buttonal.get(chushihua)).setBounds(new Rectangle(-50,
						-50, zhidanlength, zhidanlength));
				chushihua++;
		}
		//重置游戏开始标志参数
		if(startpanel == true)
		gamexunhuan = true;
		//重置飞机位置和样子
		jButton.setIcon(new ImageIcon(fileLoc));
		jButton.setLocation(320, 320);
		//让飞机首先拥有对键盘的监听权利，防止代表子弹的按钮对键盘监听，屏蔽玩家操作
		jButton.requestFocus();
		//重置玩家当前位置
		p = jButton.getLocation();
		x=p.getX();
		y=p.getY();
		//重置开始时间
		firsttime=new Date().getTime();
	}
//****************重置游戏函数	end**************//
	
//******************开启子弹发射线程函数***************//
		public void gogo() {
			int chushihua = 0;
			while (chushihua < zidanshu) 
			{
				//依次开启每个子弹对应的控制线程
				((Thread) threadal.get(chushihua)).start();
				chushihua++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
				
			}
		}
//******************开启子弹发射线程函数	end***************//
	
//****************生成开始游戏按钮*****************//
	private	JButton getKaiShi()
	{
		if(kaishi == null)
		{
			kaishi = new JButton();
			kaishi.setBounds(new Rectangle(300,300,100,50));
			kaishi.setText("开始游戏");
			kaishi.setVisible(true);
			kaishi.addActionListener
			(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					//隐藏开始界面标签
					kaishi.setVisible(false);
					gamename.setVisible(false);
					jieshao.setVisible(false);
					zuidajilu.setVisible(false);
					//显示飞机
					jButton.setVisible(true);
					//切换到游戏界面标志
					startpanel = true;
					//重置游戏
					reset();
				}
			}
					
			);
			
		}
		return kaishi;
	}
	
//****************生成开始游戏按钮	end*****************//
	
//************************生成游戏介绍按钮***************************//
		private	JButton getJieShao()
		{
			if(jieshao == null)
			{
				jieshao = new JButton();
				jieshao.setBounds(new Rectangle(300,400,100,50));
				jieshao.setText("游戏说明");
				jieshao.setVisible(true);
				jieshao.addActionListener
				(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						//生成游戏介绍对话框
						String s1 = "使用↑↓←→来移动飞机躲避子弹，尽可能的存活长的时间！";
						JOptionPane.showMessageDialog(null, s1,"游戏说明",JOptionPane.PLAIN_MESSAGE);
					
					}
				}
						
				);
				
			}
			return jieshao;
		}
		
//*************************生成游戏介绍按钮	end****************************//
		
//*******************生成最大记录按钮********************//
		private JButton getZuiDaJiLu()
		{
			if(zuidajilu == null)
			{
				zuidajilu = new JButton();
				zuidajilu.setBounds(new Rectangle(300,500,100,50));
				zuidajilu.setText("最大记录");
				zuidajilu.setVisible(true);
				zuidajilu.addActionListener
				(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						int max;
						//得到最大记录
						max = getBestRecord();
						Integer g = new Integer(max);
						//生成最大记录对话框
						String s1 = "游戏最大记录是 ："+g.toString()+"秒！";
						JOptionPane.showMessageDialog(null, s1,"游戏最大记录",JOptionPane.PLAIN_MESSAGE);
											
					}
				}
						
				);
				
			}
			return zuidajilu;
		}
//*******************生成最大记录按钮		end********************//	
		
//****************生成返回主界面按钮*****************//
	private	JButton getFanHui()
	{
		if(fanhui == null)
		{
			fanhui = new JButton();
			fanhui.setBounds(new Rectangle(150, 361, 164, 51));
			fanhui.setText("返回主界面");
			fanhui.setVisible(false);
			fanhui.addActionListener
			(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					//隐藏游戏结束界面组件
					chongxinkaishi.setVisible(false);
					jLabel.setVisible(false);
					fanhui.setVisible(false);
					//隐藏飞机
					jButton.setVisible(false);
					//显示主界面组件
					kaishi.setVisible(true);
					gamename.setVisible(true);
					jieshao.setVisible(true);
					zuidajilu.setVisible(true);
					//切换游戏界面标志标志
					startpanel = false;
					//重置游戏
					reset();
				}
			}
					
			);
			
		}
		return fanhui;
	}
//****************生成返回主界面按钮	end*****************//	
	
//**********************生成重新开始按钮**********************//	
 	private JButton getChongXinKaiShi() 
	{
		//生成重新开始按钮
		if (chongxinkaishi == null) 
		{
			chongxinkaishi = new JButton();
			chongxinkaishi.setBounds(new Rectangle(478, 361, 164, 51));
			chongxinkaishi.setText("重新开始");
			chongxinkaishi.setVisible(false);
			chongxinkaishi.addActionListener
			//重新开始按钮事件：隐藏重新开始按钮和jLabel，重置游戏
			(new java.awt.event.ActionListener() 
			{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//隐藏游戏结束界面的按钮
					chongxinkaishi.setVisible(false);
					jLabel.setVisible(false);
					fanhui.setVisible(false);
					try 
					{
							Thread.sleep(500);
					} 
					catch (InterruptedException e1) 
					{
						// TODO 自动生成 catch 块
						e1.printStackTrace();
					}
						reset();
	
				}
			});
		}
		return chongxinkaishi;
	}
//**********************生成重新开始按钮	end**************//	
 	
//*******************生成玩家人物*************************//
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
 				//给button添加处理函数，button响应键盘上的↑↓←→键
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
//*******************生成玩家人物	end********************//
 	
	
//**************************生成游戏面板函数*******************//	
	private JPanel getJPanel() 
	{
		if (jPanel == null) 
		{
			//生成游戏面板
			jPanel = new newPanel();
			jPanel.setLayout(null);//绝对布局
			jPanel.setForeground(new Color(1, 1, 1));
			jPanel.setBackground(new Color(1, 1, 1));
			jPanel.setVisible(true);
			//生成游戏结束时候用来提示的标签
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(42, -33, 595, 308));
			jLabel.setFont(new Font("Dialog", Font.BOLD, 24));
			jLabel.setForeground(Color.red);
			jLabel.setEnabled(true);
			jLabel.setVisible(false);
			jPanel.add(jLabel, null);
			//生成游戏开始时候用来提示的标签
			gamename = new JLabel();
			gamename.setBounds(new Rectangle(220, -33, 595, 308));
			gamename.setFont(new Font("Dialog", Font.BOLD, 40));
			gamename.setForeground(Color.red);
			gamename.setEnabled(true);
			gamename.setVisible(true);
			gamename.setText("谁能坚持30秒？");
			jPanel.add(gamename,null);
			//生成开始游戏按钮
			jPanel.add(getKaiShi(),null);
			//生成游戏介绍按钮
			jPanel.add(getJieShao(),null);
			//生成最大记录按钮
			jPanel.add(getZuiDaJiLu(),null);
			//生成玩家控制的人物
			jPanel.add(getJButton(), null);
			//生成重新开始按钮
			jPanel.add(getChongXinKaiShi(), null);
			//生成返回主界面按钮
			jPanel.add(getFanHui(),null);
			
		}
			return jPanel;
	}
//**************************生成游戏面板函数	end*******************//
		

//******************获取最大记录******************//
	public int getBestRecord()
	{	
		String num;
		int max = 0;
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("MyGameIcons/记录.txt"));
			
			num = in.readLine();
			max = Integer.parseInt(num);
			in.close();
		}
		catch(IOException e) { 
            e.printStackTrace(); 
        } 
		return max;
	}

//******************获取最大记录	end******************//
	
//******************将最大记录写入文件*********************//
	public void writeBestRecord(int x)
	{
		Integer num;
		try
		{
			num = new Integer(x);
			BufferedWriter out = 
	                new BufferedWriter(new FileWriter("MyGameIcons/记录.txt"));
			out.write(num.toString());
			out.close();
		}
		catch(IOException e) { 
            e.printStackTrace(); 
        } 
		return ;
	}
	
//******************将最大记录写入文件	end*********************//		
	
//**********************玩家移动人物线程函数******************//	
		class Move implements Runnable 
		{
			public void run() 
			{
				while(true)
				{
					while (gamexunhuan) 
					{
						//得到人物当前位置
						p = jButton.getLocation();
						//按住向上键
						if (kup) 
						{
							//按住向左键
							if (kleft) {
								x = p.getX();
								y = p.getY();
								if (x > 0 && y > 0) {
									//向上向左移动
									jButton.setLocation((int) x - step, (int) y
											- step);
								}
							} 
							//向右
							else if (kright)
							{
								x = p.getX();
								y = p.getY();
								if (x + 40 < 700 && y > 0) 
								{
									//上向右移动
									jButton.setLocation((int) x + step, (int) y
											- step);
								}
							} 
							//只向上
							else 
							{
								x = p.getX();
								y = p.getY();
								if (y > 0) {
									jButton.setLocation((int) x, (int) y - step);
								}
							}
						}
						//按住向下键
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
							//每隔0.01秒移动一次
							Thread.sleep(10);
						} 
						catch (InterruptedException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
					try 
					{
						//每隔0.05秒查看gamexunhuan值判断游戏是否开始
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}
			}
		}
//**********************玩家移动人物线程函数 	end******************//	

	
//*******************子弹飞行线程处理函数******************//
	class Threads implements Runnable 
	{
		//在开始函数中会生成子弹并且将一个子弹传送过来，jb是要发射的子弹
		public Threads(JButton jjb) {
			jb = jjb;
		}
		
		JButton jb = null;
		//first表示该线程控有的子弹button是否添加到panel中显示出来,开始的时候必然有一颗子弹
		private boolean first = true;

		public void run() {
			while (true) {
				go();
			}
		}

		public void go() {
			//zy,zx为子弹发射位置，随机生成
			//zzx和zzy为子弹与物体之间的步长，子弹每走一步是
			int zzx = 0;
			int zzy = 0;
			int zx = 0;
			int zy = 0;
			while (true) {
				//用gamexunhuan全局变量来控制所有线程，true表示游戏运行，false表示游戏结束
				if(gamexunhuan)
				{
					int fangxiang = (int) (Math.random() * 4 + 1);
					// 四个if随即从四个边发射子弹
					if (fangxiang == 1) {
						//从左边的边发射
						zx = 0;
						zy = (int) (Math.random() * 701);
					}
					if (fangxiang == 2) {
						//从上边的边发射
						zx = (int) (Math.random() * 701);
						zy = 0;
					}
					if (fangxiang == 3) {
						//从右边的边发射
						zx = 700;
						zy = (int) (Math.random() * 701);
					}
					if (fangxiang == 4) {
						//从下边的边发射
						zx = (int) (Math.random() * 701);
						zy = 700;
					}
					// 初始化子弹，当first为false的时候表示jb已经添加到面板中
					// 只需要重置位置即可不用再添加
					if (first) {
						jPanel.add(jb, null);
						first = false;
					}
					jb.setBounds(new Rectangle(zx, zy, 10, 10));
					// 定义子弹与物体之间的步长
					//x,y为玩家当前位置
					//玩家长宽为30，子弹长宽为10
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
							//判定的碰撞条件
							if (zx + zhidanlength/2 > x 
								& zx + zhidanlength/2 < x + jButtonlength 
								& zy + zhidanlength/2 > y
								& zy + zhidanlength/2 < y + jButtonlength) 
							{
								//若撞上子弹，玩家人物按钮上的图标变化
								jButton.setIcon(new ImageIcon(fileLoc1));
								//通过gamexunhuan让所有线程全部停止
								gamexunhuan = false;
								//子弹button要重新添加到面板上
								first = true;
								//显示游戏结束界面组件
								chongxinkaishi.setVisible(true);
								jLabel.setVisible(true);
								fanhui.setVisible(true);
								//计算游戏时间
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
										endtime=min + "分  " + sec + "秒";
								}else{
									endtime=sec + "秒";
								}
								//标签内容为所用时间
								jLabel.setText("                          GAME OVER!!! \n用时：" + endtime);
								//判断是不是最大记录
								usetime = min*60+sec;
								jilu = getBestRecord();
								if(usetime > jilu)
								{
									Integer a;
									a = new Integer(usetime);
									writeBestRecord(usetime);
									String s1 = "恭喜你打破最大记录！你的成绩是 ："+a.toString()+" 秒";
									JOptionPane.showMessageDialog(null, s1,"游戏最大记录",JOptionPane.PLAIN_MESSAGE);	
								}
								break;
							}
							// 超出边线停止循环,子弹重新生成随机位置发射
							if (zx > 700 | zy > 700 | zx < 0 | zy < 0) {
								break;
							}
							//子弹每间隔0.06秒走一步
							Thread.sleep(60);
						} 
						catch (InterruptedException e) 
						{
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
					try {
						//如果为子弹超出边界情况，表示0.05秒后重置子弹位置
						//如果为游戏结束，表示每隔0.05秒检查游戏结束标志一次
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
	}
//*******************子弹飞行线程处理函数	end******************//
	
//***********************绘制游戏面板的类************************//
 		class newPanel extends JPanel
		{
			public newPanel()
			{
				
			}
			//给新面板添加背景
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
//					//以下代码表示当窗口大于图片时候保证图片填满窗口
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

//***********************绘制游戏面板	end************************//
	
//*************************************全局变量***********************************//
		//游戏开始时间
		private long firsttime;
		//游戏结束时间
		private long lasttime;
		//游戏的面板
		private newPanel jPanel = null;
		//jbutton表示玩家操控的人物，用按钮控件来实现
		private JButton jButton = null;
		//人物边长和子弹边长
		private	int jButtonlength = 50;
		private int zhidanlength = 10;
		//kup,kdown,kleft,kright表示用户按键
		//当变量为TRUE时候表示用户分别按住了键
		//当变量为false时候表示用户松开了键
		private boolean kup ;
		private boolean kdown ;
		private boolean kleft ;
		private boolean kright ;
		// 定义玩家的行走步伐，数值越大，移动速度越快
		private int step = 5;
		//用来记录玩家当前位置
		Point p; 
		double x = 0.0;
		double y = 0.0;
		// 定义了子弹的个数
		int zidanshu = 20;
		// 表示游戏是否继续的字段，为true表示游戏继续，为false表示玩家死亡，游戏结束
		private boolean gamexunhuan = false;
		//表示当前界面是游戏界面还是开始界面
		//true表示游戏界面，此时用reset重置游戏会将gamexunhuan设置为true，游戏重新开始
		//false表示开始界面，此时用reset重置游戏会将不会把gamexunhuan设置为true，回到主界面
		private boolean startpanel = false;
		//游戏开始界面显示的控件
		private JLabel gamename = null;//游戏名字
		private JButton kaishi = null;//开始游戏
		private JButton jieshao = null;//游戏介绍按钮
		private JButton zuidajilu = null;//最大记录按钮
		//游戏结束有用来显示游戏结果的控件
		private JLabel jLabel = null;//玩家成绩
		private JButton chongxinkaishi = null;//重新开始
		private JButton fanhui = null;//返回主界面
		//用来存放子弹和每个子弹对应的控制线程的字段
		private ArrayList buttonal = new ArrayList();
		private ArrayList threadal = new ArrayList();
		//用来放到玩家控制人物上的图标的链接
		String fileLoc = "MyGameIcons/飞机.gif";
		String fileLoc1 = "MyGameIcons/死亡.gif";
		String fileLoc2 = "MyGameIcons/背景.jpg";
		String fileLoc3 = "MyGameIcons/子弹.gif";
		
//********************************全局变量	end***************************************//
} 