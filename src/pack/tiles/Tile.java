package pack.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Tile is for
 * managing tiles
 */
public class Tile {

    //STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
//    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(0);

    //CLASS

    public static final int TILEWIDTH = 100, TILEHEIGHT = 100;

    protected BufferedImage texture;
    protected final int id;

    /**
     * Constructor get Buffered image and
     * id of tile
     * @param texture
     * @param id
     */
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }


}