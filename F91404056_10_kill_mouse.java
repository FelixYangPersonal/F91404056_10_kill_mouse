import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class F91404056_10_kill_mouse extends JApplet implements MouseListener,ActionListener
{

	//存放背景圖與顯示背景
	private ImageIcon kill_mouse;						
	private JLabel kill_mouse_label;					

	//宣告亂數處理變數
	private int b1,b2;						

	//存放地鼠圖形與顯示地鼠陣列
	private ImageIcon appear_mouse;						
	private JLabel appear_mouselabel[][]  = new JLabel[5][5];		

	//設定值
	private int level;							//等級
	private int experience;						//經驗值
	private int experience_max;						//最大經驗值
	private int life;							//生命值
	private int life_max;						//最大生命值
	private int ability;							//能力值
	private int ability_max;						//最大能力值
	private int speed;							//速度值
	private int kill_mouse_num;						//打死地鼠數量
	private int mouse_speed;						//設定地鼠出現速度
	private Timer tm[] = new Timer[4];							//設定計時器

	//存放等級數
	private ImageIcon level_image,level_image2[] = new ImageIcon[4];
	private JLabel level_imagelabel,level_imagelabel2[] = new JLabel[4];

	//存放經驗數
	private ImageIcon experience_image,experience_image2[] = new ImageIcon[4],experience_max_image,experience_max_image2[] = new ImageIcon[4];
	private JLabel experience_imagelabel,experience_imagelabel2[] = new JLabel[4],experience_max_imagelabel,experience_max_imagelabel2[] = new JLabel[4];

	//存放生命數
	private ImageIcon life_image,life_image2[] = new ImageIcon[4],life_max_image,life_max_image2[] = new ImageIcon[4];
	private JLabel life_imagelabel,life_imagelabel2[] = new JLabel[4],life_max_imagelabel,life_max_imagelabel2[] = new JLabel[4];

	//存放能力數
	private ImageIcon ability_image,ability_image2[] = new ImageIcon[4],ability_max_image,ability_max_image2[] = new ImageIcon[4];
	private JLabel ability_imagelabel,ability_imagelabel2[] = new JLabel[4],ability_max_imagelabel,ability_max_imagelabel2[] = new JLabel[4];

	//存放速度數
	private ImageIcon speed_image,speed_image2[] = new ImageIcon[4];
	private JLabel speed_imagelabel,speed_imagelabel2[] = new JLabel[4];

	//存放殺死地鼠數
	private ImageIcon kill_mouse_num_image,kill_mouse_num_image2[] = new ImageIcon[10];
	private JLabel kill_mouse_num_imagelabel,kill_mouse_num_imagelabel2[] = new JLabel[10];

	//存放計時器
	private ImageIcon tmh_image,tmm_image,tms_image,tmh_image2[] = new ImageIcon[2],tmm_image2[] = new ImageIcon[2],tms_image2[] = new ImageIcon[2];
	private JLabel tmh_imagelabel,tmm_imagelabel,tms_imagelabel,tmh_imagelabel2[] = new JLabel[2],tmm_imagelabel2[] = new JLabel[2],tms_imagelabel2[] = new JLabel[2];

	//數字陣列
	private String num_image[]={"0","1","2","3","4","5","6","7","8","9"};
	private String tmph,tmpm,tmps;
	private int tmphi,tmpmi,tmpsi;
	private String H,M,S;
	private String H0,M0,S0;
	private int s1,s2,m1,m2,m3,h1,h2;
	private int a1,a2,a3;
	
	//宣告等級處理變數
	private String level_num,level_str;
	private int level_length_int;
	
	//宣告經驗值與經驗最大值處理變數
	private String experience_num,experience_max_num,experience_str,experience_max_str;
	private int experience_length_int,experience_max_length_int;

	//宣告生命值與生命最大值處理變數
	private String life_num,life_max_num,life_str,life_max_str;
	private int life_length_int,life_max_length_int;

	//宣告能力值與能力最大值處理變數
	private String ability_num,ability_max_num,ability_str,ability_max_str;
	private int ability_length_int,ability_max_length_int;

	//宣告速度處理變數
	private String speed_num,speed_str;
	private int speed_length_int;

	//宣告打死地鼠數量處理變數
	private String kill_mouse_num_nm,kill_mouse_num_str;
	private int kill_mouse_num_length_int;

	//宣告計時器-時、分、秒處理變數
	private String tmh_num,tmm_num,tms_num;
	private int tmh_length_int,tmm_length_int,tms_length_int;

	//自定游標
	private Image aiming_point;
	private Cursor aiming_point_cursor;
	private Toolkit TK;
	private MediaTracker MT;
	
	//滑鼠左鍵按下
	private boolean MOUSE_LEFT_PRESSED;


	public void init()
	{

		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		c.setSize(600,370);
		c.setVisible(true);

		//設定游標
		aiming_point = getImage(getDocumentBase(),"images/aimingpoint.png");
		MT = new MediaTracker(this);
		MT.addImage(aiming_point,0);
		TK = getToolkit();
		
		try
		{
			
			MT.waitForAll();
			
		}
		catch(InterruptedException e) {	}
		
		//建立遊標
		try
		{
			
			Point pt = new Point(15,15);
			aiming_point_cursor = TK.createCustomCursor(aiming_point,pt,"aimingpoint");
			
		}
		catch(IndexOutOfBoundsException e) { }
		setCursor(aiming_point_cursor);

		level=1;							//等級
		experience=0;						//經驗值
		experience_max=10;						//最大經驗值
		life=20;							//生命值
		life_max=20;						//最大生命值
		ability=5;							//能力值
		ability_max=5;						//最大能力值
		speed=1;							//速度值
		kill_mouse_num=0;						//打死地鼠數量
		mouse_speed=1;						//設定地鼠出現速度
		Timer tm1,tm2;							//設定計時器

		tmph="00";
		tmpm="00";
		tmps="00";
		tmphi=0;
		tmpmi=0;
		tmpsi=0;

		try 
		{	

			for(int j=0 ; j<5  ; j++)
			{
				
				for(int i=0 ; i<5 ;i++)
				{

					//設定所有死亡地鼠出現位置陣列
					appear_mouse = new ImageIcon(new URL(getCodeBase(),"./images/lifemouse.png")); 
					appear_mouselabel[j][i] = new JLabel(appear_mouse);
					appear_mouselabel[j][i].setVisible(false);
					appear_mouselabel[j][i].setSize(41,59);
					appear_mouselabel[j][i].setLocation(24+(49+41)*i,18+(1+59)*j);
					appear_mouselabel[j][i].addMouseListener(this);
					c.add(appear_mouselabel[j][i]);
					
				}
				
			}

			//設置一開始經驗值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				level_image2[i] = new ImageIcon(); 
				level_imagelabel2[i] = new JLabel(level_image2[i]);
				level_imagelabel2[i].setVisible(false);
				level_imagelabel2[i].setSize(11,14);
				level_imagelabel2[i].setLocation(585-11*i,23);
				c.add(level_imagelabel2[i]);
				
			}

			//取得等級並做處理
			level_str = String.valueOf(level);
			level_length_int = level_str.length();
			
			//判斷等級是否兩位數以上並設定一開始等級圖形與位置
			if(level_length_int >= 2)
			{

				for(int i=0 ; i<level_length_int ; i++)
				{
					
					level_num = level_str.substring(i,i+1);
					level_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(level_num)]+".png")); 
					level_imagelabel = new JLabel(level_image);
					level_imagelabel.setVisible(true);
					level_imagelabel.setSize(11,14);
					level_imagelabel.setLocation(585-11*(level_length_int-i-1),23);
					level_imagelabel.addMouseListener(this);
					c.add(level_imagelabel);
					
				}
			
			}
			else
			{
			
				level_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[level]+".png")); 
				level_imagelabel = new JLabel(level_image);
				level_imagelabel.setVisible(true);
				level_imagelabel.setSize(11,14);				
				level_imagelabel.setLocation(585,23);
				level_imagelabel.addMouseListener(this);
				c.add(level_imagelabel);
				
			}

			//設置一開始經驗值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				experience_image2[i] = new ImageIcon(); 
				experience_imagelabel2[i] = new JLabel(experience_image2[i]);
				experience_imagelabel2[i].setVisible(false);
				experience_imagelabel2[i].setSize(11,14);
				experience_imagelabel2[i].setLocation(524-11*i,63);
				c.add(experience_imagelabel2[i]);
				
			}

			//設置一開始經驗值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				experience_max_image2[i] = new ImageIcon(); 
				experience_max_imagelabel2[i] = new JLabel(experience_max_image2[i]);
				experience_max_imagelabel2[i].setVisible(false);
				experience_max_imagelabel2[i].setSize(11,14);
				experience_max_imagelabel2[i].setLocation(549+11*i,103);
				c.add(experience_max_imagelabel2[i]);
				
			}

			//取得經驗值並做處理
			experience_str = String.valueOf(experience);
			experience_length_int = experience_str.length();
			//取得經驗最大值並做處理
			experience_max_str = String.valueOf(experience_max);
			experience_max_length_int = experience_max_str.length();
			
			//判斷經驗值是否兩位數以上並設定一開始經驗值圖形與位置
			if(experience_length_int >= 2)
			{

				for(int i=0 ; i<experience_length_int ; i++)
				{
					
					experience_num = experience_str.substring(i,i+1);
					experience_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(experience_num)]+".png")); 
					experience_imagelabel = new JLabel(experience_image);
					experience_imagelabel.setVisible(true);
					experience_imagelabel.setSize(11,14);
					experience_imagelabel.setLocation(524-11*(experience_length_int-i-1),63);
					experience_imagelabel.addMouseListener(this);
					c.add(experience_imagelabel);
	
				}
			
			}
			else
			{
			
				experience_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[experience]+".png")); 
				experience_imagelabel = new JLabel(experience_image);
				experience_imagelabel.setVisible(true);
				experience_imagelabel.setSize(11,14);				
				experience_imagelabel.setLocation(524,63);
				experience_imagelabel.addMouseListener(this);
				c.add(experience_imagelabel);
				
			}

			//判斷經驗最大值是否兩位數以上並設定一開始經驗最大值圖形與位置
			if(experience_max_length_int >= 2)
			{

				for(int i=0 ; i<experience_max_length_int ; i++)
				{
					
					experience_max_num = experience_max_str.substring(i,i+1);
					experience_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(experience_max_num)]+".png")); 
					experience_max_imagelabel = new JLabel(experience_max_image);
					experience_max_imagelabel.setVisible(true);
					experience_max_imagelabel.setSize(11,14);
					experience_max_imagelabel.setLocation(549+11*i,63);
					experience_max_imagelabel.addMouseListener(this);
					c.add(experience_max_imagelabel);
	
				}
			
			}
			else
			{
			
				experience_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[experience_max]+".png")); 
				experience_max_imagelabel = new JLabel(experience_max_image);
				experience_max_imagelabel.setVisible(true);
				experience_max_imagelabel.setSize(11,14);				
				experience_max_imagelabel.setLocation(549,63);
				experience_max_imagelabel.addMouseListener(this);
				c.add(experience_max_imagelabel);
				
			}

			//設置一開始生命值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				life_image2[i] = new ImageIcon(); 
				life_imagelabel2[i] = new JLabel(life_image2[i]);
				life_imagelabel2[i].setVisible(false);
				life_imagelabel2[i].setSize(11,14);
				life_imagelabel2[i].setLocation(524-11*i,103);
				c.add(life_imagelabel2[i]);
				
			}

			//設置一開始生命最大值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				life_max_image2[i] = new ImageIcon(); 
				life_max_imagelabel2[i] = new JLabel(life_max_image2[i]);
				life_max_imagelabel2[i].setVisible(false);
				life_max_imagelabel2[i].setSize(11,14);
				life_max_imagelabel2[i].setLocation(549+11*i,103);
				c.add(life_max_imagelabel2[i]);
				
			}

			//取得生命值並做處理
			life_str = String.valueOf(life);
			life_length_int = life_str.length();
			//取得生命最大值並做處理
			life_max_str = String.valueOf(life_max);
			life_max_length_int = life_max_str.length();
			
			//判斷生命值是否兩位數以上並設定一開始生命值圖形與位置
			if(life_length_int >= 2)
			{

				for(int i=0 ; i<life_length_int ; i++)
				{
					
					life_num = life_str.substring(i,i+1);
					life_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(life_num)]+".png")); 
					life_imagelabel = new JLabel(life_image);
					life_imagelabel.setVisible(true);
					life_imagelabel.setSize(11,14);
					life_imagelabel.setLocation(524-11*(life_length_int-i-1),103);
					life_imagelabel.addMouseListener(this);
					c.add(life_imagelabel);
					
				}
			
			}
			else
			{
			
				life_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[life]+".png")); 
				life_imagelabel = new JLabel(life_image);
				life_imagelabel.setVisible(true);
				life_imagelabel.setSize(11,14);				
				life_imagelabel.setLocation(524,103);
				life_imagelabel.addMouseListener(this);
				c.add(life_imagelabel);
				
			}

			//判斷生命最大值是否兩位數以上並設定一開始生命最大值圖形與位置
			if(life_max_length_int >= 2)
			{

				for(int i=0 ; i<life_max_length_int ; i++)
				{
					
					life_max_num = life_max_str.substring(i,i+1);
					life_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(life_max_num)]+".png")); 
					life_max_imagelabel = new JLabel(life_max_image);
					life_max_imagelabel.setVisible(true);
					life_max_imagelabel.setSize(11,14);
					life_max_imagelabel.setLocation(549+11*i,103);
					life_max_imagelabel.addMouseListener(this);
					c.add(life_max_imagelabel);
	
				}
			
			}
			else
			{
			
				life_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[life_max]+".png")); 
				life_max_imagelabel = new JLabel(life_max_image);
				life_max_imagelabel.setVisible(true);
				life_max_imagelabel.setSize(11,14);				
				life_max_imagelabel.setLocation(549,103);
				life_max_imagelabel.addMouseListener(this);
				c.add(life_max_imagelabel);
				
			}

			//設置一開始能力值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				ability_image2[i] = new ImageIcon(); 
				ability_imagelabel2[i] = new JLabel(ability_image2[i]);
				ability_imagelabel2[i].setVisible(false);
				ability_imagelabel2[i].setSize(11,14);
				ability_imagelabel2[i].setLocation(524-11*i,143);
				c.add(life_imagelabel2[i]);
				
			}

			//設置一開始能力最大值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				ability_max_image2[i] = new ImageIcon(); 
				ability_max_imagelabel2[i] = new JLabel(ability_max_image2[i]);
				ability_max_imagelabel2[i].setVisible(false);
				ability_max_imagelabel2[i].setSize(11,14);
				ability_max_imagelabel2[i].setLocation(549+11*i,143);
				c.add(ability_max_imagelabel2[i]);
				
			}

			//取得能力值並做處理
			ability_str = String.valueOf(ability);
			ability_length_int = ability_str.length();
			//取得能力最大值並做處理
			ability_max_str = String.valueOf(ability_max);
			ability_max_length_int = ability_max_str.length();

			//判斷能力值是否兩位數以上並設定一開始能力值圖形與位置
			if(ability_length_int >= 2)
			{

				for(int i=0 ; i<ability_length_int ; i++)
				{
					
					ability_num = ability_str.substring(i,i+1);
					ability_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(ability_num)]+".png")); 
					ability_imagelabel = new JLabel(ability_image);
					ability_imagelabel.setVisible(true);
					ability_imagelabel.setSize(11,14);
					ability_imagelabel.setLocation(524-11*(ability_length_int-i-1),143);
					ability_imagelabel.addMouseListener(this);
					c.add(ability_imagelabel);
					
				}
			
			}
			else
			{
			
				ability_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[ability]+".png")); 
				ability_imagelabel = new JLabel(ability_image);
				ability_imagelabel.setVisible(true);
				ability_imagelabel.setSize(11,14);				
				ability_imagelabel.setLocation(524,143);
				ability_imagelabel.addMouseListener(this);
				c.add(ability_imagelabel);
				
			}

			//判斷能力最大值是否兩位數以上並設定一開始能力最大值圖形與位置
			if(ability_max_length_int >= 2)
			{

				for(int i=0 ; i<ability_max_length_int ; i++)
				{
					
					ability_max_num = ability_max_str.substring(i,i+1);
					ability_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(ability_max_num)]+".png")); 
					ability_max_imagelabel = new JLabel(ability_max_image);
					ability_max_imagelabel.setVisible(true);
					ability_max_imagelabel.setSize(11,14);
					ability_max_imagelabel.setLocation(549+11*i,143);
					ability_max_imagelabel.addMouseListener(this);
					c.add(ability_max_imagelabel);
	
				}
			
			}
			else
			{
			
				ability_max_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[ability_max]+".png")); 
				ability_max_imagelabel = new JLabel(ability_max_image);
				ability_max_imagelabel.setVisible(true);
				ability_max_imagelabel.setSize(11,14);				
				ability_max_imagelabel.setLocation(549,143);
				ability_max_imagelabel.addMouseListener(this);
				c.add(ability_max_imagelabel);
				
			}

			//取得速度值並做處理
			speed_str = String.valueOf(speed);
			speed_length_int = speed_str.length();

			//設置一開始速度值圖形與位置*4
			for(int i=0 ; i<4 ; i++)
			{
			
				speed_image2[i] = new ImageIcon(); 
				speed_imagelabel2[i] = new JLabel(speed_image2[i]);
				speed_imagelabel2[i].setVisible(false);
				speed_imagelabel2[i].setSize(11,14);
				speed_imagelabel2[i].setLocation((549-11*i),143);
				c.add(speed_imagelabel2[i]);
				
			}

			//判斷速度值是否兩位數以上並設定一開始速度值圖形與位置
			if(speed_length_int >= 2)
			{

				for(int i=0 ; i<speed_length_int ; i++)
				{
					
					speed_num = ability_str.substring(i,i+1);
					speed_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(speed_num)]+".png")); 
					speed_imagelabel = new JLabel(speed_image);
					speed_imagelabel.setVisible(true);
					speed_imagelabel.setSize(11,14);
					speed_imagelabel.setLocation(585-11*(speed_length_int-i-1),183);
					speed_imagelabel.addMouseListener(this);
					c.add(speed_imagelabel);
					
				}
			
			}
			else
			{
			
				speed_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[speed]+".png")); 
				speed_imagelabel = new JLabel(speed_image);
				speed_imagelabel.setVisible(true);
				speed_imagelabel.setSize(11,14);				
				speed_imagelabel.setLocation(585,183);
				speed_imagelabel.addMouseListener(this);
				c.add(speed_imagelabel);
				
			}

			//取得殺死地鼠量並做處理
			kill_mouse_num_str = String.valueOf(kill_mouse_num);
			kill_mouse_num_length_int = kill_mouse_num_str.length();

			//設置一開始殺死地鼠量圖形與位置*10
			for(int i=0 ; i<10 ; i++)
			{
			
				kill_mouse_num_image2[i] = new ImageIcon(); 
				kill_mouse_num_imagelabel2[i] = new JLabel(kill_mouse_num_image2[i]);
				kill_mouse_num_imagelabel2[i].setVisible(false);
				kill_mouse_num_imagelabel2[i].setSize(11,14);
				kill_mouse_num_imagelabel2[i].setLocation((567-11*i),243);
				c.add(kill_mouse_num_imagelabel2[i]);
				
			}

			//判斷殺死地鼠量是否兩位數以上並設定一開始殺死地鼠量圖形與位置
			if(kill_mouse_num_length_int >= 2)
			{

				for(int i=0 ; i<kill_mouse_num_length_int ; i++)
				{
					
					kill_mouse_num_nm = kill_mouse_num_str.substring(i,i+1);
					kill_mouse_num_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(kill_mouse_num_nm)]+".png")); 
					kill_mouse_num_imagelabel = new JLabel(kill_mouse_num_image);
					kill_mouse_num_imagelabel.setVisible(true);
					kill_mouse_num_imagelabel.setSize(11,14);
					kill_mouse_num_imagelabel.setLocation(567-11*(kill_mouse_num_length_int-i-1),243);
					kill_mouse_num_imagelabel.addMouseListener(this);
					c.add(kill_mouse_num_imagelabel);
	
				}
			
			}
			else
			{
			
				kill_mouse_num_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[kill_mouse_num]+".png")); 
				kill_mouse_num_imagelabel = new JLabel(kill_mouse_num_image);
				kill_mouse_num_imagelabel.setVisible(true);
				kill_mouse_num_imagelabel.setSize(11,14);				
				kill_mouse_num_imagelabel.setLocation(567,243);
				kill_mouse_num_imagelabel.addMouseListener(this);
				c.add(kill_mouse_num_imagelabel);
				
			}

			//取得計時器-時並做處理
			tmh_length_int = tmph.length();
			//取得計時器-分並做處理
			tmm_length_int = tmpm.length();
			//取得計時器-秒並做處理
			tms_length_int = tmps.length();

			//設置一開始計時器-時圖形與位置*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tmh_image2[i] = new ImageIcon(); 
				tmh_imagelabel2[i] = new JLabel(tmh_image2[i]);
				tmh_imagelabel2[i].setVisible(false);
				tmh_imagelabel2[i].setSize(11,14);
				tmh_imagelabel2[i].setLocation((495-11*i),293);
				c.add(tmh_imagelabel2[i]);
				
			}

			//設置一開始計時器-分圖形與位置*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tmm_image2[i] = new ImageIcon(); 
				tmm_imagelabel2[i] = new JLabel(tmm_image2[i]);
				tmm_imagelabel2[i].setVisible(false);
				tmm_imagelabel2[i].setSize(11,14);
				tmm_imagelabel2[i].setLocation((531-11*i),293);
				c.add(tmm_imagelabel2[i]);
				
			}

			//設置一開始計時器-秒圖形與位置*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tms_image2[i] = new ImageIcon(); 
				tms_imagelabel2[i] = new JLabel(tms_image2[i]);
				tms_imagelabel2[i].setVisible(false);
				tms_imagelabel2[i].setSize(11,14);
				tms_imagelabel2[i].setLocation((567-11*i),293);
				c.add(tms_imagelabel2[i]);
				
			}

			//判斷計時器-時是否兩位數以上並設定一開始計時器-時圖形與位置
			if(tmh_length_int >= 2)
			{

				for(int i=0 ; i<tmh_length_int ; i++)
				{
					
					tmh_num = tmph.substring(i,i+1);
					tmh_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(tmh_num)]+".png")); 
					tmh_imagelabel = new JLabel(tmh_image);
					tmh_imagelabel.setVisible(false);
					tmh_imagelabel.setSize(11,14);
					tmh_imagelabel.setLocation(495-11*(tmh_length_int-i-1),293);
					c.add(tmh_imagelabel);
	
				}
			
			}

			//判斷計時器-分是否兩位數以上並設定一開始計時器-分圖形與位置
			if(tmm_length_int >= 2)
			{

				for(int i=0 ; i<tmm_length_int ; i++)
				{
					
					tmm_num = tmpm.substring(i,i+1);
					tmm_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(tmm_num)]+".png")); 
					tmm_imagelabel = new JLabel(tmm_image);
					tmm_imagelabel.setVisible(false);
					tmm_imagelabel.setSize(11,14);
					tmm_imagelabel.setLocation(531-11*(tmm_length_int-i-1),293);
					c.add(tmm_imagelabel);
	
				}
			
			}

			//判斷計時器-秒是否兩位數以上並設定一開始計時器-秒圖形與位置
			if(tms_length_int >= 2)
			{

				for(int i=0 ; i<tms_length_int ; i++)
				{
					
					tms_num = tmps.substring(i,i+1);
					tms_image = new ImageIcon(new URL(getCodeBase(),"./images/"+num_image[Integer.parseInt(tms_num)]+".png")); 
					tms_imagelabel = new JLabel(tms_image);
					tms_imagelabel.setVisible(false);
					tms_imagelabel.setSize(11,14);
					tms_imagelabel.setLocation(567-11*(tms_length_int-i-1),293);
					c.add(tms_imagelabel);
	
				}
			
			}

			//設定背景
			kill_mouse = new ImageIcon(new URL(getCodeBase(),"./images/mouse.png")); 
			kill_mouse_label = new JLabel(kill_mouse);
			kill_mouse_label.setVisible(true);
			kill_mouse_label.setSize(600,370);
			kill_mouse_label.setLocation(0,0);
			c.add(kill_mouse_label);

		}
		catch(MalformedURLException e)
		{

			System.out.println("下載圖形檔發生錯誤      URL:"+e+"\n"); 
			return;

		}
		
		//設定計數器
		tm[0]=new Timer(1000/1,this);
		tm[0].start();
		for(int i=1 ; i<4 ; i++)
		{
					
			switch (i)
			{
				
				case 1:
				
					tm[1]=new Timer(2000/mouse_speed,this);
					break;
				
				case 2:
			
					tm[2]=new Timer(4000/mouse_speed,this);
					break;
				
				case 3:
			
					tm[3]=new Timer(3000/mouse_speed,this);
					break;
			}
			
			//啟動計數器
			tm[i].start();		
			
		}

		//開啟左鍵
 		MOUSE_LEFT_PRESSED = true;

	}
	
	//設定地鼠動作事件
	public void actionPerformed(ActionEvent e)
	{

		for(int t=1 ; t<4 ; t++)
		{
		
			if(e.getSource() == tm[t])
			{
				
				for(int j=0 ; j<5 ; j++)
				{
		
					for(int i=0 ; i<5 ; i++)
					{

						appear_mouselabel[j][i].setVisible(false);
						//解決打死地鼠出現的位置圖形無法更新
						appear_mouselabel[j][i].setIcon(new ImageIcon("./images/lifemouse.png"));
				
					}
			
				}
			
				//地鼠亂數出現
 				b1=(int)(Math.random()*5);
 				b2=(int)(Math.random()*5);
 				appear_mouselabel[b1][b2].setVisible(true);
 				
 			}
			
		}

		if(e.getSource() == tm[0])
		{
		
			//啟動所有數字初始值
			//level_imagelabel.setVisible(true);
			//experience_imagelabel.setVisible(true);
			//experience_max_imagelabel.setVisible(true);
			//ability_imagelabel.setVisible(true);
			//ability_max_imagelabel.setVisible(true);
			//ability_imagelabel.setVisible(true);
			//ability_max_imagelabel.setVisible(true);
			//speed_imagelabel.setVisible(true);
			//kill_mouse_num_imagelabel.setVisible(true);
			tmh_imagelabel.setVisible(true);
			tmm_imagelabel.setVisible(true);
			tms_imagelabel.setVisible(true);
		
			tmpsi+=1;
		
			a1=tmpsi;
			s2=m3=60;					//設定分、秒每60進位
			h2=24;						//設定時每24進位
			a2=s2*m3*h2;					//求出一天秒數86400秒
			a3=a1%a2;					//求出剩餘秒數
			s1=a3%s2;					//求出秒數
			m2=a3/s2;					//求出剩餘分數
			m1=m2%m3;					//求出分數
			h1=m2/m3;					//求出時數

			//判斷時顯示
			if(h1 == 0)
			{
		
				H="00";
		
			}
			else
			{
		
				H0=String.valueOf(h1);

				//取得計時器-時並做處理
				tmh_length_int = H0.length();
		
				if(tmh_length_int >= 2)
		    	{

					H=H0;
			
				}
				else
				{
				
					H="0"+H0;
				
				}

			}

			//判斷分顯示
			if(m1 == 0)
			{
			
				M="00";
		
			}
			else
			{
		
				M0=String.valueOf(m1);
			
				//取得計時器-分並做處理
				tmm_length_int = M0.length();
		
				if(tmm_length_int >= 2)
		   		 {

					M=M0;
			
				}
				else
				{
							
					M="0"+M0;
				}
			
			}

			//判斷秒顯示
			if(s1 == 0)
			{
				
				S="00";
		
			}
			else
			{
			
				S0=String.valueOf(s1);
			
				//取得計時器-秒並做處理
				tms_length_int = S0.length();			

				if(tms_length_int >= 2)
		   		 {

					S=S0;
			
				}
				else
				{
							
					S="0"+S0;
				}
			
			}

			//取得計時器-時並做處理
			tmh_length_int = H.length();
			//取得計時器-分並做處理
			tmm_length_int = M.length();
			//取得計時器-秒並做處理
			tms_length_int = S.length();

			if(tmpsi < 60)
			{
			 		
 				//判斷計時器-秒是否兩位數以上並設定一開始計時器-秒圖形與位置
				if(tms_length_int >= 2)
				{

					tms_imagelabel.setVisible(false);

					for(int i=0 ; i<tms_length_int ; i++)
					{
					
						tms_num = S.substring(i,i+1);
						tms_imagelabel2[i].setVisible(true);
						tms_imagelabel2[i].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(tms_num)]+".png"));
						tms_imagelabel2[i].setLocation(555+11*i,293);
	
					}
			
				}
				else
				{
				
					tms_imagelabel.setIcon(new ImageIcon("./images/"+num_image[s1]+".png"));
					tms_imagelabel.setLocation(567,293);
					tms_imagelabel.setVisible(true);
					
				}
				
				tmpmi+=1;
			
			}
			else
			{
				
				if(tmpmi < 60)
				{
				
 					//判斷計時器-秒是否兩位數以上並設定一開始計時器-秒圖形與位置
					if(tmm_length_int >= 2)
					{

						tmm_imagelabel.setVisible(true);

						for(int i=0 ; i<tmm_length_int ; i++)
						{
					
							tmm_num = S.substring(i,i+1);
							tmm_imagelabel.setVisible(false);
							tmm_imagelabel2[i].setVisible(true);
							tmm_imagelabel2[i].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(tmm_num)]+".png"));
							tmm_imagelabel2[i].setLocation(519+11*i,293);
	
						}
			
					}
					else
					{
				
						tmm_imagelabel.setIcon(new ImageIcon("./images/"+num_image[m1]+".png"));
						tmm_imagelabel.setLocation(531,293);
						tmm_imagelabel.setVisible(true);
					
					}

					tmphi+=1;
				
				}
				
				if(tmphi < 24)
				{
					
					//判斷計時器-秒是否兩位數以上並設定一開始計時器-秒圖形與位置
					if(tmh_length_int >= 2)
					{

						tmh_imagelabel.setVisible(true);

						for(int i=0 ; i<tmh_length_int ; i++)
						{
					
							tmh_num = S.substring(i,i+1);
							tmh_imagelabel.setVisible(false);
							tmh_imagelabel2[i].setVisible(true);
							tmh_imagelabel2[i].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(tmh_num)]+".png"));
							tmh_imagelabel2[i].setLocation(483+11*i,293);
	
						}
			
					}
					else
					{
				
						tmh_imagelabel.setIcon(new ImageIcon("./images/"+num_image[h1]+".png"));
						tmh_imagelabel.setLocation(495,293);
						tmh_imagelabel.setVisible(true);
						
					}
					
				}

			}
			
		}
		
		//開啟左鍵
 		MOUSE_LEFT_PRESSED = true;		
 		
	}
	
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	//按一下滑鼠左鍵打地鼠
	public void mouseClicked(MouseEvent e)
	{
		
		for(int j=0 ; j<5 ; j++)
		{
		
			for(int i=0 ; i<5 ; i++)
			{
			
				//判斷是否在地鼠上面按下滑鼠鍵、判斷按下滑鼠鍵是否等於1、左鍵是否開啟
				if(e.getSource() == appear_mouselabel[j][i] && e.getClickCount() == 1 && MOUSE_LEFT_PRESSED == true)
				{
					
					appear_mouselabel[j][i].setIcon(new ImageIcon("./images/diemouse.png"));

					//開啟左鍵
					MOUSE_LEFT_PRESSED = false;

					//增加經驗值
					experience+=2;
					//取得經驗值並做處理
					experience_str = String.valueOf(experience);
					experience_length_int = experience_str.length();

					//判斷經驗值是否兩位數以上並設定經驗值值圖形與位置
					if(experience_length_int >= 2)
					{

						experience_imagelabel.setVisible(false);

						for(int k=0 ; k<experience_length_int ; k++)
						{
					
							experience_num = experience_str.substring(k,k+1);
							experience_imagelabel2[k].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(experience_num)]+".png"));
							//experience_imagelabel.setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(experience_num)]+".png"));
							//experience_imagelabel2[k].setLocation(524-11*(experience_length_int-k-1),63);
							//experience_imagelabel2[k].setVisible(true);
								
						}
							
					}
					else
					{

						experience_imagelabel.setIcon(new ImageIcon("./images/"+num_image[experience]+".png"));
						experience_imagelabel.setLocation(524,63);
						experience_imagelabel.setVisible(true);
						
					}

					if(experience == experience_max)
					{
			
						//當等級提升時，更新經驗值、經驗最大值、生命值、生命最大值
						level+=1;
						experience = 0;
						/*
						//取得更新後的經驗值並做處理
						experience_str = String.valueOf(experience);
						experience_length_int = experience_str.length();

						//更新經驗值值圖形
						for(int k=0 ; k<experience_length_int ; k++)
						{
					
							experience_imagelabel2[k].setVisible(false);
							
						}
						*/
						experience_max +=10;
						
						//取得更新後的經驗最大值並做處理
						experience_max_str = String.valueOf(experience_max);
						experience_max_length_int = experience_max_str.length();

						//判斷更新後的經驗最大值是否兩位數以上並設定經驗最大值值圖形與位置
						if(experience_max_length_int >= 2)
						{

							experience_max_imagelabel.setVisible(false);

							for(int k=0 ; k<experience_max_length_int ; k++)
							{
					
								experience_max_num = experience_max_str.substring(k,k+1);
								experience_max_imagelabel2[k].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(experience_max_num)]+".png"));
								experience_max_imagelabel2[k].setLocation(524+11*k,63);
								experience_max_imagelabel2[k].setVisible(true);
								
							}
							
						}
						
						life_max +=50;
			
						//等級提升後如果殺死地鼠數量增加，打死一隻地鼠回血+2
						if(level > 2)
						{

							if(life <= life_max) life+=2;;					

							//取得經驗值並做處理
							level_str = String.valueOf(level);
							level_length_int = level_str.length();

							//判斷經驗值是否兩位數以上並設定經驗值值圖形與位置
							if(level_length_int >= 2)
							{

								level_imagelabel.setVisible(false);
								
								for(int k=0 ; k<level_length_int ; k++)
								{
					
									level_num = level_str.substring(k,k+1);
									level_imagelabel2[k].setIcon(null);
									level_imagelabel2[k].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(level_num)]+".png"));
									level_imagelabel2[k].setLocation(524-11*(level_length_int-k-1),103);
									level_imagelabel2[k].setVisible(true);
								
								}
							
							}
							else
							{

								level_imagelabel.setIcon(new ImageIcon("./images/"+num_image[level]+".png"));
								level_imagelabel.setLocation(524,103);	
								level_imagelabel.setVisible(true);
						
							}							
							
						}

					}

					kill_mouse_num+=1;

					//取得經驗值並做處理
					kill_mouse_num_str = String.valueOf(kill_mouse_num);
					kill_mouse_num_length_int = kill_mouse_num_str.length();

					//判斷經驗值是否兩位數以上並設定經驗值值圖形與位置
					if(kill_mouse_num_length_int >= 2)
					{

						kill_mouse_num_imagelabel.setVisible(false);

						for(int k=0 ; k<kill_mouse_num_length_int ; k++)
						{
					
							kill_mouse_num_nm = kill_mouse_num_str.substring(k,k+1);
							kill_mouse_num_imagelabel2[k].setIcon(new ImageIcon("./images/"+num_image[Integer.parseInt(kill_mouse_num_nm)]+".png"));
							kill_mouse_num_imagelabel2[k].setLocation(567-11*(kill_mouse_num_length_int-k-1),243);
							kill_mouse_num_imagelabel2[k].setVisible(true);
								
						}
							
					}
					else
					{

						kill_mouse_num_imagelabel.setIcon(new ImageIcon("./images/"+num_image[kill_mouse_num]+".png"));
						kill_mouse_num_imagelabel.setLocation(567,243);
						kill_mouse_num_imagelabel.setVisible(true);
						
					}
	
				}
				else
				{
					
					life-=5;
					
				}
				
			}

		}
		
	}
	
	/*
	//設定鍵盤事件
	public void keyPressed(KeyEvent evt)
	{
	
		int key = evt.getKeyCode();	
		
		switch (key)
		{
			
			case keyEvent.VK_K:
				//以能力值判斷按下一次K殺死地鼠數目
				ability=ability-10;
				break;
		}
		
	} */
/*
	//設定地鼠出現多執行緒副程式
	class mouse_runnable implements Runnable
	{
				
		public void run()
		{
			
			for(int i =0 ; i<10000 ; i++)
			{
			
				b1 = (int)(Math.random()*5);
				b2 = (int)(Math.random()*5);
				//appear_mouselabel[b1][b2].setVisible(true);
				System.out.println(b1+" "+b2);
			
				try { Thread.sleep(2000); }
				catch(InterruptedException e) { return; }

			}

		}

	}*/
/*
	//地鼠處理機類別
	class mouse_handle
	{

		int[] handle = new int[2];
		int point;
		
		public mouse_handle() { point=-1; }
		
		public synchronized void appear_mouse(int v)
		{
			
			try
			{
				
				while (point >= 2)
				{
					
					
					
				}
				
			}
			
		}
		
	}
*/
}