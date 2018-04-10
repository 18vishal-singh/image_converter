
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;

/*
@author:Vishal Singh
*/

public class Image_converter implements ActionListener, KeyListener
{

	JFrame frame;
	JPanel jp;
	static JLabel jl;
	JLabel jl1,jl2,jl3,jl4;
	JButton jb,jb1,jb2;
	@SuppressWarnings("rawtypes")
	JComboBox format;
	static JTextField jtf1,jtf2,jtf3;
	JFileChooser jfc;
	String Input_path="";
	String Output_path="C:\\";
	String Output_path1="";
	String file_name="";
	String selected_format="png";
	float ratio=1;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	Image_converter() throws Exception
	{
		frame=new JFrame("Resize the image");
		
		String[] formats={"jpg","png","gif","bmp"};
		
		String path = "default.jpg";
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        BufferedImage outputImage = new BufferedImage(250,200, image.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, 250, 200, null);
        g2d.dispose();
        
 //       jl = new JLabel(new ImageIcon(outputImage));
        jl=new JLabel();
        jl.setIcon(new ImageIcon(outputImage));
        jl.setBounds(50, 20, 250, 200);
        jl.setBorder(BorderFactory.createLineBorder(Color.black,5));
        
        jb=new JButton("UPLOAD IMAGE");
        jb.setBounds(100,240,150,25);
        jb.addActionListener(this);
        
        jl1 = new JLabel("Change to format : ");
        jl1.setBounds(80,280,100,25);
        format = new JComboBox(formats);
        format.setSelectedIndex(0);
        format.setBounds(180, 280, 90, 25);
        format.addActionListener(new ActionListener()
        						{
            						public void actionPerformed(ActionEvent e)	
            						{
            							selected_format="";
            							selected_format = String.valueOf(format.getSelectedItem());
            							Output_path=Output_path1+selected_format;
            							jtf3.setText(Output_path);
            						}
            						
            						
            					}
            					);
        
        jl2=new JLabel("Dimension : ");
        jl2.setBounds(70,320,100,25);
        jtf1=new JTextField();
        jtf1.setBounds(135,320,70,25);
        jtf1.addKeyListener(this);
        jl3=new JLabel("X");
        jl3.setBounds(210,320,10,25);
        jtf2=new JTextField();
        jtf2.setBounds(220,320,70,25);
        
        jb1=new JButton("CONVERT");
        jb1.setBounds(100,360,150,25);
        jb1.addActionListener(this);
        
        jl4=new JLabel("Save at : ");
        jl4.setBounds(50,400,50,25);
        jtf3=new JTextField();
        jtf3.setBounds(95,400,200,25);
        
        jb2 = new JButton("BROWSE");
        jb2.setBounds(100,440,150,25);
        jb2.addActionListener(this);
        
       
        frame.add(jl);
        frame.add(jb);
        frame.add(jl1);
        frame.add(format);
        frame.add(jl2);
        frame.add(jtf1);
        frame.add(jl3);
        frame.add(jtf2);
        frame.add(jb1);
        frame.add(jl4);
        frame.add(jtf3);
        frame.add(jb2);
        
        frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	frame.setSize(360,550);
    	frame.setLocation(500, 100);
	}

	public static void main(String s[])
	{
		try 
		{
            		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Image_converter();
        	} 
		catch (Exception exc) 
		{
            		System.err.println("Error loading L&F: " + exc);
        	}
		
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("BROWSE"))
		{
			jfc=new JFileChooser(Output_path);
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int r=jfc.showOpenDialog(null);
			if(r==JFileChooser.APPROVE_OPTION)
			{
				Output_path=""+jfc.getSelectedFile();
				Output_path=Output_path.trim();
				jtf3.setText(Output_path);
			}
		}
		if(e.getActionCommand().equals("UPLOAD IMAGE"))
		{
			try
			{
				jfc=new JFileChooser("D:\\photo");
				int r=jfc.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION)
				{
					Input_path=""+jfc.getSelectedFile();
					Input_path=Input_path.trim();
					file_name=jfc.getSelectedFile().getName();
					file_name=file_name.substring(0,file_name.indexOf("."));
					Output_path1=jfc.getSelectedFile().getParentFile()+"\\new_"+file_name+".";//   +selected_format;
					Output_path=Output_path1+selected_format;
					jtf3.setText(Output_path);
				
				}
			
				File file = new File(Input_path);
				BufferedImage image = ImageIO.read(file);
				BufferedImage outputImage = new BufferedImage(250,200, image.getType());
				Graphics2D g2d = outputImage.createGraphics();
				g2d.drawImage(image, 0, 0, 250, 200, null);
				g2d.dispose();
				jl.setIcon(new ImageIcon(outputImage));
				
				jtf1.setText(""+image.getWidth());
				jtf2.setText(""+image.getHeight());
				ratio=((float)image.getHeight()/(float)image.getWidth());
	//			System.out.println(ratio);
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null,"UPLOADING ERROR !!\nTry again.");
			}
		}
		
		if(e.getActionCommand().equals("CONVERT"))
		{
			try
			{
				Output_path=jtf3.getText();
			//	System.out.println(Output_path);
		//		String t="D:\\photo\\abc1.png";
				int width,height;
				width=Integer.parseInt(jtf1.getText().trim());
				height=Integer.parseInt(jtf2.getText().trim());
				File file = new File(Input_path);
				BufferedImage image = ImageIO.read(file);
				BufferedImage outputImage = new BufferedImage(width,height, image.getType());
				Graphics2D g2d = outputImage.createGraphics();
				g2d.drawImage(image, 0, 0, width, height, null);
				g2d.dispose();
				String formatName = Output_path.substring(Output_path.lastIndexOf(".") + 1);
				ImageIO.write(outputImage, formatName, new File(Output_path));
				
				JOptionPane.showMessageDialog(null," DONE.\nCheck on "+Output_path);
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null," ERROR !!\nTry again.");
			}
			catch(Throwable ex)
			{
				JOptionPane.showMessageDialog(null," OUT OF MEMORY !!\nEnter less dimension.");
			}
			
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		try
		{
			int wid=Integer.parseInt(jtf1.getText().trim());
			jtf2.setText(""+(int)((ratio)*(wid)));
			
		}
		catch(Exception ex)
		{
			jtf2.setText("ERROR");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
