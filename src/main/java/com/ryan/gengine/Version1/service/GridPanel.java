package com.ryan.gengine.Version1.service;

import com.ryan.gengine.Version1.display.GamePanel;
import com.ryan.gengine.Version1.service.singles.HoverableTile;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by a689638 on 8/14/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */

public class GridPanel extends GamePanel{
    private GTile current;
    private Dimension panelDimension;
    private Dimension eachTileSize;

    @Override
    public void mouseDragged(MouseEvent e) {
        GTile g = getContentAtRaw(e.getPoint());
        if (g == null || current == null) {
            return;
        }
        if (g != current) {
            if (current instanceof ClickableTile) {
                ((ClickableTile) current).clickUp();
            }
            if (current instanceof HoverableTile) {
                ((HoverableTile) current).hoverOff();
            }
            if (g instanceof HoverableTile) {
                ((HoverableTile) g).hoverOn();
            }
            current = g;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GTile g = getContentAtRaw(e.getPoint());
        if (g == null || current == null) {
            return;
        }
        if (g != current) {
            if (current instanceof HoverableTile) {
                ((HoverableTile) current).hoverOff();
            }
            if (g instanceof HoverableTile) {
                ((HoverableTile) g).hoverOn();
            }
            current = g;
        }
    }

    class Grid {
        class Tile {
            private GTile content;
            private Point location;

            public Tile() {
                this.location = new Point(0, 0);
            }

            public Tile(Point p) {
                this.location = p;
            }

            public Tile(GTile content, Point location) {
                this.content = content;
                this.content.location = location;
                this.location = location;
            }

            public void setLocation(Point p) {
                this.location = p;
                this.content.setLocation(p);
            }

            public GTile getContent() {
                return content;
            }

            public void setContent(GTile content) {
                this.content = content;
            }
        }

        private Tile[][] tiles;

        Grid(Dimension dimension) {
            tiles = new Tile[dimension.width][dimension.height];
        }


    }

    private Grid grid;

    public GridPanel(Dimension panelDim, Dimension tileSize) {
        super();
        this.panelDimension = panelDim;
        this.eachTileSize = tileSize;
        grid = new Grid(panelDimension);
        grid.tiles = new Grid.Tile[panelDimension.width][panelDimension.height];
        for (int i = 0; i < grid.tiles.length; i++) {
            for (int j = 0; j < grid.tiles[i].length; j++) {
                this.grid.tiles[i][j] = grid.new Tile(new Point(tileSize.width * i, j * tileSize.width));
            }
        }
        current = getContentAt(0, 0);

    }

    public Dimension getPanelDimension() {
        return panelDimension;
    }

    public void setPanelDimension(Dimension panelDimension) {
        this.panelDimension = panelDimension;
    }

    public Dimension getEachTileSize() {
        return eachTileSize;
    }

    public void setEachTileSize(Dimension eachTileSize) {
        this.eachTileSize = eachTileSize;
    }

    public GTile getContentAt(int locationA, int locationB) {
        return grid.tiles[locationA][locationB].content;
    }

    public GTile getContentAt(Point location) {
        return grid.tiles[location.x][location.y].getContent();
    }

    public void setContentAt(int locationA, int locationB, GTile o) {
        grid.tiles[locationA][locationB].content = o;
    }
    public void setContentAt(Point p, GTile o) {
        grid.tiles[p.x][p.y].content = o;
    }


    public GTile getContentAtRaw(Point p) {

        int x = p.x / eachTileSize.width;
        int y = p.y / eachTileSize.height;
        Point p2 = new Point(x, y);
        try {
            return getContentAt(p2);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    public <T extends GTile> void setGrid(java.util.List<java.util.List<T>> grid) {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                T tile = grid.get(i).get(j);
                this.grid.tiles[i][j].setContent(tile);
                this.grid.tiles[i][j].setLocation(tile.location);
            }
        }
    }

    public <T extends GTile> void setGrid(T[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                this.grid.tiles[i][j].content = grid[i][j];
                this.grid.tiles[i][j].setLocation(new Point(i, j));
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Grid.Tile[] x : grid.tiles) {
            for (Grid.Tile y : x) {
                if (y.getContent() != null) {
                    y.getContent().draw(g);
                }

            }
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        GTile tile = getContentAtRaw(e.getPoint());
        if (tile != null && tile instanceof ClickableTile) {
            ((ClickableTile) tile).click(e.getButton() == 1);
        }
        rpaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        GTile tile = getContentAtRaw(e.getPoint());
        current = tile;
        if (tile != null && tile instanceof ClickableTile) {
            ((ClickableTile) tile).clickDown();
        }
        rpaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GTile tile = getContentAtRaw(e.getPoint());

        if (tile != null && tile instanceof ClickableTile) {
            if (tile == current) {
                ((ClickableTile) tile).clickUp();
            } else {
                if (current instanceof ClickableTile) {
                    ((ClickableTile) current).clickUp();
                }
            }

        }
        rpaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}