package com.gruber.pfr.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;

import com.gruber.pfr.graphics.elements.Coordinates;
import com.gruber.pfr.graphics.elements.Vector;

public class GraphWizard extends JApplet {

	public static enum FunctionType {

		SINGLE_POINT, LINE_STRAIGHT// , LINE_QUADRATIC, LINE_CUBIC
	}

	class GraphicsSettings {

		Visualization vis;
		Dimension dim;
		FunctionType type;

		public void setVis(Visualization vis) {
			this.vis = vis;
		}

		public Dimension getDim() {
			return dim;
		}

		public void setDim(Dimension dim) {
			this.dim = dim;
		}

		public FunctionType getType() {
			return type;
		}

		public void setType(FunctionType type) {
			this.type = type;
		}
	}

	class GraphPainter extends Component {

		GraphicsSettings settings;

		public GraphPainter(GraphicsSettings settings) {
			super();
			this.settings = settings;
		}

		public Dimension getPreferredSize() {
			return this.settings.dim;
		}

		public void paint(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;
			Composite origComposite;

			origComposite = g2.getComposite();

			// draw the functions
			Iterator<List<Vector>> paths = this.settings.vis.getCurves().iterator();

			while (paths.hasNext()) {

				Iterator<Vector> points = paths.next().iterator();
				Vector vec = points.next(); // the first point

				if (vec != null) {

					GeneralPath path = new GeneralPath();

					Coordinates coord = this.settings.vis.getCoordinates();

					// transform the coordinates
					int offX = coord.getMinX();
					int offY = coord.getMaxY();
					int sizeX = coord.getMaxX() - coord.getMinX();
					int sizeY = coord.getMaxY() - coord.getMinY();
					float expX = new Float(this.settings.dim.getWidth()).floatValue() / new Float(sizeX).floatValue();
					float expY = new Float(this.settings.dim.getHeight()).floatValue() / new Float(sizeY).floatValue();

					// draw the coordinate system
					g2.setColor(Color.BLACK);
					float cX;
					float cY;
					
					if (coord.getMaxX() > 0 && coord.getMinX() < 0)
						cX = - offX * expX;
					else if (coord.getMaxX() < 0)
						cX = 1;
					else
						cX = sizeX - 1;
					
					if (coord.getMaxY() > 0 && coord.getMinY() < 0)
						cY = offY * expY;
					else if (coord.getMaxY() < 0)
						cY = sizeY;
					else
						cY = 0;
					g2.draw(new Line2D.Float(cX, 0, cX, new Float(this.settings.dim.getHeight()).floatValue()));
					g2.draw(new Line2D.Float(0,cY, new Float(this.settings.dim.getWidth()).floatValue(), cY));
					
					// the first point
					float posX = (vec.getOrigin().getCoordinates()[0] - offX) * expX;
					float posY = (offY - vec.getOrigin().getCoordinates()[1]) * expY;
					path.moveTo(posX, posY);
					g2.setColor(Color.BLACK);
					g2.draw(new Ellipse2D.Float(posX - 1, posY - 1, 2, 2));

					g2.setColor(Color.BLUE);
					if (vec.getDirection() != null) {
						float vecX = posX + vec.getDirection().getCoordinates()[0] * expX;
						float vecY = posY - vec.getDirection().getCoordinates()[1] * expY;
						g2.draw(new Line2D.Float(posX, posY, vecX, vecY));
					}

					while (points.hasNext()) {

						vec = points.next();
						posX = (vec.getOrigin().getCoordinates()[0] - offX) * expX;
						posY = (offY - vec.getOrigin().getCoordinates()[1]) * expY;

						if (this.settings.type.equals(FunctionType.SINGLE_POINT))
							path.moveTo(posX, posY);

						g2.setColor(Color.BLACK);
						g2.draw(new Ellipse2D.Float(posX - 1, posY - 1, 2, 2));
//						g2.drawString(new Float(vec.getOrigin().getCoordinates()[0]).toString() + '-' + new Float(vec.getOrigin().getCoordinates()[1]).toString(), posX, posY);
						path.lineTo(posX, posY);

						if (vec.getDirection() != null) {
							float vecX = posX + vec.getDirection().getCoordinates()[0] * expX;
							float vecY = posY - vec.getDirection().getCoordinates()[1] * expY;
							g2.setColor(Color.BLUE);
							g2.draw(new Line2D.Float(posX, posY, vecX, vecY));
						}
					}
					g2.setColor(Color.BLACK);
					g2.draw(path);
				}
			}
			g2.setComposite(origComposite);

		}
	}

	GraphicsSettings settings;

	public GraphWizard(Visualization vis, Dimension dim, FunctionType type) {
		super();
		this.settings = new GraphicsSettings();
		this.settings.setVis(vis);
		this.settings.setDim(dim);
		this.settings.setType(type);
	}

	GraphPainter graphP;

	public void start() {

		setLayout(new BorderLayout());

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		add("North", p);

		graphP = new GraphPainter(this.settings);
		p.add("Center", graphP);
	}
}
