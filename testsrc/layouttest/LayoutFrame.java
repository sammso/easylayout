package layouttest;
import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sohlman.easylayout.Position;
import com.sohlman.easylayout.EasyLayout;

/**
 * @author Sampsa Sohlman
 * @version 13.5.2003
 */
public class LayoutFrame extends JFrame
{
	/**
	 * 
	 */
	public LayoutFrame()
	{
		super();

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try
		{
			createControls();
		}
		catch (Exception l_Exception)
		{
			l_Exception.printStackTrace();
		}
	}

	protected void createControls()
	{
		setTitle("EasyLayout TestApplication");
		JPanel l_JPanel_ContentPane = (JPanel)this.getContentPane();
		int[] li_columnsPercentages = { 0, 100, 0 };
		int[] li_rowPercentages = { 0, 0, 50, 50 };

		EasyLayout l_EasyLayout = new EasyLayout(null, null, li_columnsPercentages, li_rowPercentages, 3, 3);
		l_JPanel_ContentPane.setLayout(l_EasyLayout);

		JButton l_JButton_Ok = new JButton("Ok");
		JButton l_JButton_Cancel = new JButton("Cancel");
		JLabel l_JLabel_Text = new JLabel("Salutation");
		JTextField l_JTextField_Text1 = new JTextField("Hello world");
		JTextField l_JTextField_Text2 = new JTextField("Good bye");
		JTextField l_JTextField_Text3 = new JTextField("");

		String[] lS_Items = { "Hello world", "Hola Mundo", "Terve maailma" };
		JComboBox l_JComboBox = new JComboBox(lS_Items);

		// l_JPanel_ContentPane.add(l_JButton_Ok, new Constraint(2, 0, 1, 1, Constraint.FULL, Constraint.FULL, Constraint.DEFAULT, Constraint.DEFAULT));
		l_JPanel_ContentPane.add(l_JButton_Ok, new Position(2, 0));
		l_JPanel_ContentPane.add(l_JComboBox, new Position(1,1));
		l_JPanel_ContentPane.add(l_JButton_Cancel, new Position(2, 1, 1, 1, Position.FULL, Position.FULL));
		l_JPanel_ContentPane.add(l_JTextField_Text1, new Position(1, 0, 1, 1, Position.FULL, Position.CENTER, Position.DEFAULT, Position.DEFAULT));
		l_JPanel_ContentPane.add(l_JLabel_Text, new Position(0, 0, 1, 1, Position.FULL, Position.FULL, Position.DEFAULT, Position.DEFAULT));
		l_JPanel_ContentPane.add(l_JTextField_Text2, new Position(1, 2, 1, 1, Position.CENTER, Position.CENTER, Position.DEFAULT, Position.DEFAULT));
		l_JPanel_ContentPane.add(l_JTextField_Text3, new Position(1, 3, 2, 1, Position.FULL, Position.FULL, Position.DEFAULT, Position.DEFAULT));
		pack();
	}

	//Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent a_WindowEvent)
	{
		super.processWindowEvent(a_WindowEvent);
		if (a_WindowEvent.getID() == WindowEvent.WINDOW_CLOSING)
		{
			System.exit(0);
		}
	}

	public static void main(String[] aS_Arguments)
	{
		LayoutFrame l_LayoutFrame = new LayoutFrame();
		//Validate frames that have preset sizes
		//Pack frames that have useful preferred size info, e.g. from their layout

		l_LayoutFrame.validate();

		//Center the window
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
