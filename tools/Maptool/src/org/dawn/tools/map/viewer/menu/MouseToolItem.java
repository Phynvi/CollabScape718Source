package org.dawn.tools.map.viewer.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import org.dawn.tools.map.viewer.menu.mousetools.MouseTool;

public class MouseToolItem<T extends MouseTool> extends JMenuItem {
	
	private MapMenuBar context;
	private T tool;
	
	public MouseToolItem(MapMenuBar ctx, T tool) {
		super(tool.getDescription());
		this.context = ctx;
		this.tool = tool;
		setIcon(new ImageIcon("data/map/sprites/menu/"+tool.getIconName()+"_off.png"));
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				context.getMap().removeMouseListener(context.currentItem.tool);
				context.getMap().removeMouseMotionListener(context.currentItem.tool);
				context.currentItem.setIcon(new ImageIcon("data/map/sprites/menu/"+context.currentItem.tool.getIconName()+"_off.png"));
				register();
			}
		});
	}

	public void register() {
		context.currentItem = this;
		context.getMap().addMouseListener(tool);
		context.getMap().addMouseMotionListener(tool);
		setIcon(new ImageIcon("data/map/sprites/menu/"+tool.getIconName()+"_on.png"));
		context.getMenu(1).setIcon(getIcon());
		context.getMenu(1).setText(tool.getDescription());
	}
	
	public T getTool() {
		return tool;
	}
	
}
