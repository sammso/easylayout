package layouttest;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Sampsa Sohlman
 * @version 21.5.2003
 */
public class LauncherApplet extends Applet implements ActionListener
{
	public Button i_Button_Launch;
	/**
	 * @throws java.awt.HeadlessException
	 */
	public LauncherApplet() throws HeadlessException
	{
		super();
		
		i_Button_Launch = new Button("Launch");
		i_Button_Launch.addActionListener(this);
		add(i_Button_Launch);
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent a_ActionEvent)
	{
		LayoutFrame l_LayoutFrame = new LayoutFrame();

		l_LayoutFrame.validate();
		Dimension l_Dimension_Size = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension l_Dimension_Frame = l_LayoutFrame.getSize();
		if (l_Dimension_Frame.height > l_Dimension_Size.height)
		{
			l_Dimension_Frame.height = l_Dimension_Size.height;
		}
		if (l_Dimension_Frame.width > l_Dimension_Size.width)
		{
			l_Dimension_Frame.width = l_Dimension_Size.width;
		}
		l_LayoutFrame.setLocation((l_Dimension_Size.width - l_Dimension_Frame.width) / 2, (l_Dimension_Size.height - l_Dimension_Frame.height) / 2);
		l_LayoutFrame.setVisible(true);
	}
}
