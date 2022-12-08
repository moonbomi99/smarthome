import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;



public class Serial
{
   int number;
   InputStream in; 
   OutputStream out;
   int c =0;


   JFrame frame;
   JButton airon,airoff,lighton,lightoff,windowon,windowoff,alarmon,alarmoff,tvon,tvoff,lighton2;
   JPanel panel1;

   public Serial()
   {
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("CONTROLLER");
      frame.setBounds(500, 0, 600,900);
      try {
         this.connect("COM4");
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      MyDrawPanel panel = new MyDrawPanel();
      onoffListener ofl = new onoffListener(out);
      airon=new JButton("ON");
      airon.addActionListener(ofl);
      airoff=new JButton("OFF");
      airoff.addActionListener(ofl);
      lighton=new JButton("ON");
      lighton.addActionListener(ofl);
      lighton2=new JButton("COLOR");
      lighton2.addActionListener(ofl);
      lightoff=new JButton("OFF");
      lightoff.addActionListener(ofl);
      windowon=new JButton("ON");
      windowon.addActionListener(ofl);
      windowoff=new JButton("OFF");
      windowoff.addActionListener(ofl);
      alarmon=new JButton("ON");
      alarmon.addActionListener(ofl);
      alarmoff=new JButton("OFF");
      alarmoff.addActionListener(ofl);
      tvon=new JButton("ON");
      tvon.addActionListener(ofl);
      tvoff=new JButton("OFF");
      tvoff.addActionListener(ofl);
      //CheckListener checkListener=new CheckListener(c);
      //Timer changeC=new Timer(100,checkListener);

      frame.getContentPane().add(panel);
      frame.setVisible(true);
      //changeC.start();
      // super();
   }

   void set_up() {

   }
   /*
   class CheckListener implements ActionListener{
      int c;

      public CheckListener(int number) {
         this.c = number;
      }

      public void actionPerformed(ActionEvent e) {
         System.out.println("check1");

      }
   }
    */

   void connect ( String portName ) throws Exception
   {

      CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
      if ( portIdentifier.isCurrentlyOwned() )
      {
         System.out.println("Error: Port is currently in use");
      }
      else
      {
         //클래스 이름을 식별자로 사용하여 포트 오픈
         CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

         if ( commPort instanceof SerialPort )
         {
            //포트 설정(통신속도 설정. 기본 9600으로 사용)
            SerialPort serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

            //Input,OutputStream 버퍼 생성 후 오픈
            in = serialPort.getInputStream();
            out=serialPort.getOutputStream();


            // data 읽기
            byte[] buffer = new byte[1024];
            this.in.read(buffer);
            // data 쓰기

            int c = 0;
            this.out.write(c);

         }
         else
         {
            System.out.println("Error: Only serial ports are handled by this example.");
         }
      }     
   }
   public class MyDrawPanel extends JPanel{
      public void paintComponent(Graphics g) {
         frame.setLayout(null);
         Graphics2D g2d = (Graphics2D) g;
         g2d.setColor(Color.black);
         g2d.setFont(new Font("새굴림",Font.BOLD,38));
         g2d.drawString("SMART HOME CONTROLLER", 4, 40);
         ImageIcon airimg = new ImageIcon("에어컨.png");
         ImageIcon lightimg=new ImageIcon("무드등2.png");
         ImageIcon windowimg=new ImageIcon("창문.png");
         ImageIcon alarmimg=new ImageIcon("경보.png");
         ImageIcon tvimg=new ImageIcon("티비.png");

         airon.setBackground(Color.cyan);
         airoff.setBackground(Color.cyan);
         lighton.setBackground(Color.yellow);
         lighton2.setBackground(Color.yellow);
         lightoff.setBackground(Color.yellow);
         windowon.setBackground(Color.green);
         windowoff.setBackground(Color.green);
         alarmon.setBackground(Color.red);
         alarmoff.setBackground(Color.red);
         tvon.setBackground(Color.gray);
         tvoff.setBackground(Color.gray);

         
         frame.add(airon);
         airon.setBounds(20, 70, 120, 60);
         airon.setFont(new Font("새굴림",Font.BOLD,30));
         g2d.drawImage(airimg.getImage(), 210,35, 160, 160, null);
         frame.add(lighton2);
         frame.add(airoff);
         airoff.setBounds(440,70,120,60);
         airoff.setFont(new Font("새굴림",Font.BOLD,30));
         frame.add(lighton);
         lighton.setBounds(20,200,120,60);
         lighton.setFont(new Font("새굴림",Font.BOLD,30));
         lighton2.setBounds(20,270,120,60);
         lighton2.setFont(new Font("새굴림",Font.BOLD,20));
         g2d.drawImage(lightimg.getImage(), 210,180, 160, 160, null);
         frame.add(lightoff);
         lightoff.setBounds(440,235,120,60);
         lightoff.setFont(new Font("새굴림",Font.BOLD,30));
         frame.add(windowon);
         windowon.setBounds(20,395,120,60);
         windowon.setFont(new Font("새굴림",Font.BOLD,30));
         g2d.drawImage(windowimg.getImage(), 200,350, 160, 140, null);
         frame.add(windowoff);
         windowoff.setBounds(440,395,120,60);
         windowoff.setFont(new Font("새굴림",Font.BOLD,30));
         frame.add(alarmon);
         alarmon.setBounds(20, 560, 120, 60);
         alarmon.setFont(new Font("새굴림",Font.BOLD,30));
         g2d.drawImage(alarmimg.getImage(), 220,500, 160, 160, null);
         frame.add(alarmoff);
         alarmoff.setBounds(440, 560, 120, 60);
         alarmoff.setFont(new Font("새굴림",Font.BOLD,30));
         frame.add(tvon);
         tvon.setBounds(20, 725, 120, 60);
         tvon.setFont(new Font("새굴림",Font.BOLD,30));
         g2d.drawImage(tvimg.getImage(), 210,660, 160, 160, null);
         frame.add(tvoff);
         tvoff.setBounds(440, 725, 120, 60);
        tvoff.setFont(new Font("새굴림",Font.BOLD,30));


      }
   }
   class onoffListener implements ActionListener{
      OutputStream os;
      int c=0;

      public onoffListener(OutputStream os) {
         this.os = os;
      }

      public void actionPerformed(ActionEvent e) {
         if(e.getSource()==airon) {
            try {
               this.c=1;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==lighton2) {
            try {
                 this.c=11;
                 this.os.write(c);
              } catch (IOException e1) {
                 // TODO Auto-generated catch block
                 e1.printStackTrace();
              }
         }
         else if(e.getSource()==airoff) {
            try {
               this.c=2;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==lighton) {
            try {
               this.c=3;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==lightoff) {
            try {
               this.c=4;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==windowon) {
            try {
               this.c=5;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==windowoff) {
            try {
               this.c=6;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==alarmon) {
            try {
               this.c=7;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==alarmoff) {
            try {
               this.c=8;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==tvon) {
            try {
               this.c=9;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource()==tvoff) {
            try {
               this.c=10;
               this.os.write(c);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
   }
   /*class offListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         if(e.getSource()==airoff)
            System.exit(0);
      }
   }*/

   public static void main ( String[] args ){
      try
      {
         new Serial(); //입력한 포트로 연결
      }
      catch ( Exception e )
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      //MyDrawPanelTest mdt=new MyDrawPanelTest();
   }
}