package test;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyMouseController{
    
    private Dimension dim; //存储屏幕尺寸
    private Robot robot;//自动化对象

    public MyMouseController(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("屏幕大小为：" + dim.getWidth() + " " + dim.getHeight());
        try{
            robot = new Robot();
            robot.setAutoDelay(1000);
        }catch(AWTException e){
            e.printStackTrace();
        }
    }
    
    //相对移动
    public void moveRelative(int width,int heigh){    //鼠标移动函数    
        System.out.println("enter Move()...");
        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
          System.out.println("移动前坐标：" + mousepoint.x + " " + mousepoint.y);
          width += mousepoint.x;
          heigh += mousepoint.y;
        try{
            robot.mouseMove(width,heigh);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("移动后坐标：" + width + " " + heigh);
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }
    //绝对移动
    public void moveAbsolute(Point begin ,Point end,Boolean press){
    	robot.mouseMove(begin.x, begin.y);
    	if(press){
    		robot.mousePress(this.getButton("left"));
    	}
    	robot.mouseMove(end.x, end.y);
    	if(press){
    		robot.mouseRelease(this.getButton("left"));
    	}
    }
    
    public Point getCurrentPoint(){
		return MouseInfo.getPointerInfo().getLocation();
    }
    
    public Point getPoint(int x,int y){
    	return new Point(x,y);
    }
    
    //left 获取左键 right 获取右键
    public int getButton(String type){
    	if("left".equals(type)){
    		return InputEvent.BUTTON1_MASK;
    	}else if("right".equals(type)){
    		return InputEvent.BUTTON3_MASK;
    	}
    	
    	throw new RuntimeException();
    }
    
    //全屏截图
    public void screenCapture(){
    	 Rectangle screenRect = new Rectangle(dim);
    	//截图（截取整个屏幕图片）
         BufferedImage bufferedImage =       robot.createScreenCapture(screenRect);
         String fileName ="file-"+System.currentTimeMillis()+".png";
         //保存截图
         File file = new File("C:/Users/Administrator/Desktop/郭俊浩相关/"+fileName);
         try {
			ImageIO.write(bufferedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    public static void main(String args[])throws Exception{
       /* MyMouseController mmc = new MyMouseController();
        Thread.currentThread().sleep(5000);
        Point begin = mmc.getCurrentPoint();
        System.out.println("获取起点"+begin);
        Thread.currentThread().sleep(5000);
        Point end = mmc.getCurrentPoint();
        System.out.println("获取终点"+end);
        mmc.moveAbsolute(begin, end, true);*/
        
    	new MyMouseHandle();
        
    }
}

class MyMouseHandle extends JFrame implements MouseListener {
	private JTextArea text = new JTextArea();
 
	public MyMouseHandle() {
		super.setTitle("鼠标记录");// 设置标题
		JScrollPane pane = new JScrollPane(text);// 加入滚动条
		pane.setBounds(5, 5, 300, 200);// 设置绝对位置
		super.add(pane);// 向窗体中加入组件
		text.addMouseListener(this);// 加入mouse监听
		super.setSize(310, 210);
		super.setVisible(true);
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}
 
	public void mouseClicked(MouseEvent e){
		int c = e.getButton();// 得到按下的鼠标键
		String mouseInfo = null;// 接收信息
		if (c == MouseEvent.BUTTON1)// 判断是鼠标左键按下
		{
			mouseInfo = "左键";
		} else if (c == MouseEvent.BUTTON3) {// 判断是鼠标右键按下
			mouseInfo = "右键";
		} else {
			mouseInfo = "滚轴";
		}
		System.out.println("鼠标单击：" + mouseInfo + ".\n");
	}
 
	public void mouseEntered(MouseEvent e){
		
	}
 
	public void mouseExited(MouseEvent e){
		
	}
 
	public void mousePressed(MouseEvent e){
		text.append("鼠标按下.\n");
	}
 
	public void mouseReleased(MouseEvent e){
		text.append("鼠标松开.\n");
	}
}


