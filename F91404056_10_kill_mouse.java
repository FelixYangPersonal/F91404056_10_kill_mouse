import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class F91404056_10_kill_mouse extends JApplet implements MouseListener,ActionListener
{

	//�s��I���ϻP��ܭI��
	private ImageIcon kill_mouse;						
	private JLabel kill_mouse_label;					

	//�ŧi�üƳB�z�ܼ�
	private int b1,b2;						

	//�s��a���ϧλP��ܦa���}�C
	private ImageIcon appear_mouse;						
	private JLabel appear_mouselabel[][]  = new JLabel[5][5];		

	//�]�w��
	private int level;							//����
	private int experience;						//�g���
	private int experience_max;						//�̤j�g���
	private int life;							//�ͩR��
	private int life_max;						//�̤j�ͩR��
	private int ability;							//��O��
	private int ability_max;						//�̤j��O��
	private int speed;							//�t�׭�
	private int kill_mouse_num;						//�����a���ƶq
	private int mouse_speed;						//�]�w�a���X�{�t��
	private Timer tm[] = new Timer[4];							//�]�w�p�ɾ�

	//�s�񵥯ż�
	private ImageIcon level_image,level_image2[] = new ImageIcon[4];
	private JLabel level_imagelabel,level_imagelabel2[] = new JLabel[4];

	//�s��g���
	private ImageIcon experience_image,experience_image2[] = new ImageIcon[4],experience_max_image,experience_max_image2[] = new ImageIcon[4];
	private JLabel experience_imagelabel,experience_imagelabel2[] = new JLabel[4],experience_max_imagelabel,experience_max_imagelabel2[] = new JLabel[4];

	//�s��ͩR��
	private ImageIcon life_image,life_image2[] = new ImageIcon[4],life_max_image,life_max_image2[] = new ImageIcon[4];
	private JLabel life_imagelabel,life_imagelabel2[] = new JLabel[4],life_max_imagelabel,life_max_imagelabel2[] = new JLabel[4];

	//�s���O��
	private ImageIcon ability_image,ability_image2[] = new ImageIcon[4],ability_max_image,ability_max_image2[] = new ImageIcon[4];
	private JLabel ability_imagelabel,ability_imagelabel2[] = new JLabel[4],ability_max_imagelabel,ability_max_imagelabel2[] = new JLabel[4];

	//�s��t�׼�
	private ImageIcon speed_image,speed_image2[] = new ImageIcon[4];
	private JLabel speed_imagelabel,speed_imagelabel2[] = new JLabel[4];

	//�s������a����
	private ImageIcon kill_mouse_num_image,kill_mouse_num_image2[] = new ImageIcon[10];
	private JLabel kill_mouse_num_imagelabel,kill_mouse_num_imagelabel2[] = new JLabel[10];

	//�s��p�ɾ�
	private ImageIcon tmh_image,tmm_image,tms_image,tmh_image2[] = new ImageIcon[2],tmm_image2[] = new ImageIcon[2],tms_image2[] = new ImageIcon[2];
	private JLabel tmh_imagelabel,tmm_imagelabel,tms_imagelabel,tmh_imagelabel2[] = new JLabel[2],tmm_imagelabel2[] = new JLabel[2],tms_imagelabel2[] = new JLabel[2];

	//�Ʀr�}�C
	private String num_image[]={"0","1","2","3","4","5","6","7","8","9"};
	private String tmph,tmpm,tmps;
	private int tmphi,tmpmi,tmpsi;
	private String H,M,S;
	private String H0,M0,S0;
	private int s1,s2,m1,m2,m3,h1,h2;
	private int a1,a2,a3;
	
	//�ŧi���ųB�z�ܼ�
	private String level_num,level_str;
	private int level_length_int;
	
	//�ŧi�g��ȻP�g��̤j�ȳB�z�ܼ�
	private String experience_num,experience_max_num,experience_str,experience_max_str;
	private int experience_length_int,experience_max_length_int;

	//�ŧi�ͩR�ȻP�ͩR�̤j�ȳB�z�ܼ�
	private String life_num,life_max_num,life_str,life_max_str;
	private int life_length_int,life_max_length_int;

	//�ŧi��O�ȻP��O�̤j�ȳB�z�ܼ�
	private String ability_num,ability_max_num,ability_str,ability_max_str;
	private int ability_length_int,ability_max_length_int;

	//�ŧi�t�׳B�z�ܼ�
	private String speed_num,speed_str;
	private int speed_length_int;

	//�ŧi�����a���ƶq�B�z�ܼ�
	private String kill_mouse_num_nm,kill_mouse_num_str;
	private int kill_mouse_num_length_int;

	//�ŧi�p�ɾ�-�ɡB���B��B�z�ܼ�
	private String tmh_num,tmm_num,tms_num;
	private int tmh_length_int,tmm_length_int,tms_length_int;

	//�۩w���
	private Image aiming_point;
	private Cursor aiming_point_cursor;
	private Toolkit TK;
	private MediaTracker MT;
	
	//�ƹ�������U
	private boolean MOUSE_LEFT_PRESSED;


	public void init()
	{

		Container c=getContentPane();
		c.setLayout(null);
		c.setBackground(Color.white);
		c.setSize(600,370);
		c.setVisible(true);

		//�]�w���
		aiming_point = getImage(getDocumentBase(),"images/aimingpoint.png");
		MT = new MediaTracker(this);
		MT.addImage(aiming_point,0);
		TK = getToolkit();
		
		try
		{
			
			MT.waitForAll();
			
		}
		catch(InterruptedException e) {	}
		
		//�إ߹C��
		try
		{
			
			Point pt = new Point(15,15);
			aiming_point_cursor = TK.createCustomCursor(aiming_point,pt,"aimingpoint");
			
		}
		catch(IndexOutOfBoundsException e) { }
		setCursor(aiming_point_cursor);

		level=1;							//����
		experience=0;						//�g���
		experience_max=10;						//�̤j�g���
		life=20;							//�ͩR��
		life_max=20;						//�̤j�ͩR��
		ability=5;							//��O��
		ability_max=5;						//�̤j��O��
		speed=1;							//�t�׭�
		kill_mouse_num=0;						//�����a���ƶq
		mouse_speed=1;						//�]�w�a���X�{�t��
		Timer tm1,tm2;							//�]�w�p�ɾ�

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

					//�]�w�Ҧ����`�a���X�{��m�}�C
					appear_mouse = new ImageIcon(new URL(getCodeBase(),"./images/lifemouse.png")); 
					appear_mouselabel[j][i] = new JLabel(appear_mouse);
					appear_mouselabel[j][i].setVisible(false);
					appear_mouselabel[j][i].setSize(41,59);
					appear_mouselabel[j][i].setLocation(24+(49+41)*i,18+(1+59)*j);
					appear_mouselabel[j][i].addMouseListener(this);
					c.add(appear_mouselabel[j][i]);
					
				}
				
			}

			//�]�m�@�}�l�g��ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				level_image2[i] = new ImageIcon(); 
				level_imagelabel2[i] = new JLabel(level_image2[i]);
				level_imagelabel2[i].setVisible(false);
				level_imagelabel2[i].setSize(11,14);
				level_imagelabel2[i].setLocation(585-11*i,23);
				c.add(level_imagelabel2[i]);
				
			}

			//���o���Ũð��B�z
			level_str = String.valueOf(level);
			level_length_int = level_str.length();
			
			//�P�_���ŬO�_���ƥH�W�ó]�w�@�}�l���ŹϧλP��m
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

			//�]�m�@�}�l�g��ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				experience_image2[i] = new ImageIcon(); 
				experience_imagelabel2[i] = new JLabel(experience_image2[i]);
				experience_imagelabel2[i].setVisible(false);
				experience_imagelabel2[i].setSize(11,14);
				experience_imagelabel2[i].setLocation(524-11*i,63);
				c.add(experience_imagelabel2[i]);
				
			}

			//�]�m�@�}�l�g��ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				experience_max_image2[i] = new ImageIcon(); 
				experience_max_imagelabel2[i] = new JLabel(experience_max_image2[i]);
				experience_max_imagelabel2[i].setVisible(false);
				experience_max_imagelabel2[i].setSize(11,14);
				experience_max_imagelabel2[i].setLocation(549+11*i,103);
				c.add(experience_max_imagelabel2[i]);
				
			}

			//���o�g��Ȩð��B�z
			experience_str = String.valueOf(experience);
			experience_length_int = experience_str.length();
			//���o�g��̤j�Ȩð��B�z
			experience_max_str = String.valueOf(experience_max);
			experience_max_length_int = experience_max_str.length();
			
			//�P�_�g��ȬO�_���ƥH�W�ó]�w�@�}�l�g��ȹϧλP��m
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

			//�P�_�g��̤j�ȬO�_���ƥH�W�ó]�w�@�}�l�g��̤j�ȹϧλP��m
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

			//�]�m�@�}�l�ͩR�ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				life_image2[i] = new ImageIcon(); 
				life_imagelabel2[i] = new JLabel(life_image2[i]);
				life_imagelabel2[i].setVisible(false);
				life_imagelabel2[i].setSize(11,14);
				life_imagelabel2[i].setLocation(524-11*i,103);
				c.add(life_imagelabel2[i]);
				
			}

			//�]�m�@�}�l�ͩR�̤j�ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				life_max_image2[i] = new ImageIcon(); 
				life_max_imagelabel2[i] = new JLabel(life_max_image2[i]);
				life_max_imagelabel2[i].setVisible(false);
				life_max_imagelabel2[i].setSize(11,14);
				life_max_imagelabel2[i].setLocation(549+11*i,103);
				c.add(life_max_imagelabel2[i]);
				
			}

			//���o�ͩR�Ȩð��B�z
			life_str = String.valueOf(life);
			life_length_int = life_str.length();
			//���o�ͩR�̤j�Ȩð��B�z
			life_max_str = String.valueOf(life_max);
			life_max_length_int = life_max_str.length();
			
			//�P�_�ͩR�ȬO�_���ƥH�W�ó]�w�@�}�l�ͩR�ȹϧλP��m
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

			//�P�_�ͩR�̤j�ȬO�_���ƥH�W�ó]�w�@�}�l�ͩR�̤j�ȹϧλP��m
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

			//�]�m�@�}�l��O�ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				ability_image2[i] = new ImageIcon(); 
				ability_imagelabel2[i] = new JLabel(ability_image2[i]);
				ability_imagelabel2[i].setVisible(false);
				ability_imagelabel2[i].setSize(11,14);
				ability_imagelabel2[i].setLocation(524-11*i,143);
				c.add(life_imagelabel2[i]);
				
			}

			//�]�m�@�}�l��O�̤j�ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				ability_max_image2[i] = new ImageIcon(); 
				ability_max_imagelabel2[i] = new JLabel(ability_max_image2[i]);
				ability_max_imagelabel2[i].setVisible(false);
				ability_max_imagelabel2[i].setSize(11,14);
				ability_max_imagelabel2[i].setLocation(549+11*i,143);
				c.add(ability_max_imagelabel2[i]);
				
			}

			//���o��O�Ȩð��B�z
			ability_str = String.valueOf(ability);
			ability_length_int = ability_str.length();
			//���o��O�̤j�Ȩð��B�z
			ability_max_str = String.valueOf(ability_max);
			ability_max_length_int = ability_max_str.length();

			//�P�_��O�ȬO�_���ƥH�W�ó]�w�@�}�l��O�ȹϧλP��m
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

			//�P�_��O�̤j�ȬO�_���ƥH�W�ó]�w�@�}�l��O�̤j�ȹϧλP��m
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

			//���o�t�׭Ȩð��B�z
			speed_str = String.valueOf(speed);
			speed_length_int = speed_str.length();

			//�]�m�@�}�l�t�׭ȹϧλP��m*4
			for(int i=0 ; i<4 ; i++)
			{
			
				speed_image2[i] = new ImageIcon(); 
				speed_imagelabel2[i] = new JLabel(speed_image2[i]);
				speed_imagelabel2[i].setVisible(false);
				speed_imagelabel2[i].setSize(11,14);
				speed_imagelabel2[i].setLocation((549-11*i),143);
				c.add(speed_imagelabel2[i]);
				
			}

			//�P�_�t�׭ȬO�_���ƥH�W�ó]�w�@�}�l�t�׭ȹϧλP��m
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

			//���o�����a���q�ð��B�z
			kill_mouse_num_str = String.valueOf(kill_mouse_num);
			kill_mouse_num_length_int = kill_mouse_num_str.length();

			//�]�m�@�}�l�����a���q�ϧλP��m*10
			for(int i=0 ; i<10 ; i++)
			{
			
				kill_mouse_num_image2[i] = new ImageIcon(); 
				kill_mouse_num_imagelabel2[i] = new JLabel(kill_mouse_num_image2[i]);
				kill_mouse_num_imagelabel2[i].setVisible(false);
				kill_mouse_num_imagelabel2[i].setSize(11,14);
				kill_mouse_num_imagelabel2[i].setLocation((567-11*i),243);
				c.add(kill_mouse_num_imagelabel2[i]);
				
			}

			//�P�_�����a���q�O�_���ƥH�W�ó]�w�@�}�l�����a���q�ϧλP��m
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

			//���o�p�ɾ�-�ɨð��B�z
			tmh_length_int = tmph.length();
			//���o�p�ɾ�-���ð��B�z
			tmm_length_int = tmpm.length();
			//���o�p�ɾ�-��ð��B�z
			tms_length_int = tmps.length();

			//�]�m�@�}�l�p�ɾ�-�ɹϧλP��m*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tmh_image2[i] = new ImageIcon(); 
				tmh_imagelabel2[i] = new JLabel(tmh_image2[i]);
				tmh_imagelabel2[i].setVisible(false);
				tmh_imagelabel2[i].setSize(11,14);
				tmh_imagelabel2[i].setLocation((495-11*i),293);
				c.add(tmh_imagelabel2[i]);
				
			}

			//�]�m�@�}�l�p�ɾ�-���ϧλP��m*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tmm_image2[i] = new ImageIcon(); 
				tmm_imagelabel2[i] = new JLabel(tmm_image2[i]);
				tmm_imagelabel2[i].setVisible(false);
				tmm_imagelabel2[i].setSize(11,14);
				tmm_imagelabel2[i].setLocation((531-11*i),293);
				c.add(tmm_imagelabel2[i]);
				
			}

			//�]�m�@�}�l�p�ɾ�-��ϧλP��m*2
			for(int i=0 ; i<2 ; i++)
			{
			
				tms_image2[i] = new ImageIcon(); 
				tms_imagelabel2[i] = new JLabel(tms_image2[i]);
				tms_imagelabel2[i].setVisible(false);
				tms_imagelabel2[i].setSize(11,14);
				tms_imagelabel2[i].setLocation((567-11*i),293);
				c.add(tms_imagelabel2[i]);
				
			}

			//�P�_�p�ɾ�-�ɬO�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-�ɹϧλP��m
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

			//�P�_�p�ɾ�-���O�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-���ϧλP��m
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

			//�P�_�p�ɾ�-��O�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-��ϧλP��m
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

			//�]�w�I��
			kill_mouse = new ImageIcon(new URL(getCodeBase(),"./images/mouse.png")); 
			kill_mouse_label = new JLabel(kill_mouse);
			kill_mouse_label.setVisible(true);
			kill_mouse_label.setSize(600,370);
			kill_mouse_label.setLocation(0,0);
			c.add(kill_mouse_label);

		}
		catch(MalformedURLException e)
		{

			System.out.println("�U���ϧ��ɵo�Ϳ��~      URL:"+e+"\n"); 
			return;

		}
		
		//�]�w�p�ƾ�
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
			
			//�Ұʭp�ƾ�
			tm[i].start();		
			
		}

		//�}�ҥ���
 		MOUSE_LEFT_PRESSED = true;

	}
	
	//�]�w�a���ʧ@�ƥ�
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
						//�ѨM�����a���X�{����m�ϧεL�k��s
						appear_mouselabel[j][i].setIcon(new ImageIcon("./images/lifemouse.png"));
				
					}
			
				}
			
				//�a���üƥX�{
 				b1=(int)(Math.random()*5);
 				b2=(int)(Math.random()*5);
 				appear_mouselabel[b1][b2].setVisible(true);
 				
 			}
			
		}

		if(e.getSource() == tm[0])
		{
		
			//�ҰʩҦ��Ʀr��l��
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
			s2=m3=60;					//�]�w���B��C60�i��
			h2=24;						//�]�w�ɨC24�i��
			a2=s2*m3*h2;					//�D�X�@�Ѭ��86400��
			a3=a1%a2;					//�D�X�Ѿl���
			s1=a3%s2;					//�D�X���
			m2=a3/s2;					//�D�X�Ѿl����
			m1=m2%m3;					//�D�X����
			h1=m2/m3;					//�D�X�ɼ�

			//�P�_�����
			if(h1 == 0)
			{
		
				H="00";
		
			}
			else
			{
		
				H0=String.valueOf(h1);

				//���o�p�ɾ�-�ɨð��B�z
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

			//�P�_�����
			if(m1 == 0)
			{
			
				M="00";
		
			}
			else
			{
		
				M0=String.valueOf(m1);
			
				//���o�p�ɾ�-���ð��B�z
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

			//�P�_�����
			if(s1 == 0)
			{
				
				S="00";
		
			}
			else
			{
			
				S0=String.valueOf(s1);
			
				//���o�p�ɾ�-��ð��B�z
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

			//���o�p�ɾ�-�ɨð��B�z
			tmh_length_int = H.length();
			//���o�p�ɾ�-���ð��B�z
			tmm_length_int = M.length();
			//���o�p�ɾ�-��ð��B�z
			tms_length_int = S.length();

			if(tmpsi < 60)
			{
			 		
 				//�P�_�p�ɾ�-��O�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-��ϧλP��m
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
				
 					//�P�_�p�ɾ�-��O�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-��ϧλP��m
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
					
					//�P�_�p�ɾ�-��O�_���ƥH�W�ó]�w�@�}�l�p�ɾ�-��ϧλP��m
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
		
		//�}�ҥ���
 		MOUSE_LEFT_PRESSED = true;		
 		
	}
	
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}

	//���@�U�ƹ����䥴�a��
	public void mouseClicked(MouseEvent e)
	{
		
		for(int j=0 ; j<5 ; j++)
		{
		
			for(int i=0 ; i<5 ; i++)
			{
			
				//�P�_�O�_�b�a���W�����U�ƹ���B�P�_���U�ƹ���O�_����1�B����O�_�}��
				if(e.getSource() == appear_mouselabel[j][i] && e.getClickCount() == 1 && MOUSE_LEFT_PRESSED == true)
				{
					
					appear_mouselabel[j][i].setIcon(new ImageIcon("./images/diemouse.png"));

					//�}�ҥ���
					MOUSE_LEFT_PRESSED = false;

					//�W�[�g���
					experience+=2;
					//���o�g��Ȩð��B�z
					experience_str = String.valueOf(experience);
					experience_length_int = experience_str.length();

					//�P�_�g��ȬO�_���ƥH�W�ó]�w�g��ȭȹϧλP��m
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
			
						//���Ŵ��ɮɡA��s�g��ȡB�g��̤j�ȡB�ͩR�ȡB�ͩR�̤j��
						level+=1;
						experience = 0;
						/*
						//���o��s�᪺�g��Ȩð��B�z
						experience_str = String.valueOf(experience);
						experience_length_int = experience_str.length();

						//��s�g��ȭȹϧ�
						for(int k=0 ; k<experience_length_int ; k++)
						{
					
							experience_imagelabel2[k].setVisible(false);
							
						}
						*/
						experience_max +=10;
						
						//���o��s�᪺�g��̤j�Ȩð��B�z
						experience_max_str = String.valueOf(experience_max);
						experience_max_length_int = experience_max_str.length();

						//�P�_��s�᪺�g��̤j�ȬO�_���ƥH�W�ó]�w�g��̤j�ȭȹϧλP��m
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
			
						//���Ŵ��ɫ�p�G�����a���ƶq�W�[�A�����@���a���^��+2
						if(level > 2)
						{

							if(life <= life_max) life+=2;;					

							//���o�g��Ȩð��B�z
							level_str = String.valueOf(level);
							level_length_int = level_str.length();

							//�P�_�g��ȬO�_���ƥH�W�ó]�w�g��ȭȹϧλP��m
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

					//���o�g��Ȩð��B�z
					kill_mouse_num_str = String.valueOf(kill_mouse_num);
					kill_mouse_num_length_int = kill_mouse_num_str.length();

					//�P�_�g��ȬO�_���ƥH�W�ó]�w�g��ȭȹϧλP��m
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
	//�]�w��L�ƥ�
	public void keyPressed(KeyEvent evt)
	{
	
		int key = evt.getKeyCode();	
		
		switch (key)
		{
			
			case keyEvent.VK_K:
				//�H��O�ȧP�_���U�@��K�����a���ƥ�
				ability=ability-10;
				break;
		}
		
	} */
/*
	//�]�w�a���X�{�h������Ƶ{��
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
	//�a���B�z�����O
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