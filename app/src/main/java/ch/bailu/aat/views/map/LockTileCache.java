package ch.bailu.aat.views.map;

import org.osmdroid.tileprovider.MapTile;

import ch.bailu.aat.services.ServiceContext;
import ch.bailu.aat.services.cache.LockCache;
import ch.bailu.aat.services.cache.TileStackObject;

public class LockTileCache extends TileCache {
    private final static int INITIAL_CAPACITY = 5;

    private final LockCache<TileStackObject> tiles = new LockCache(INITIAL_CAPACITY);


    @Override
    public TileStackObject get(String string) {
        for (int i = 0; i<tiles.size(); i++) {
            if (tiles.get(i).toString().equals(string)) {
                return tiles.use(i);
            }
        }
        return null;
    }

    @Override
    public TileStackObject get(MapTile mt) {
        final String mtile = mt.toString();

        for (int i = 0; i<tiles.size(); i++) {
            if (mtile.equals(tiles.get(i).getTile())) {
                return tiles.use(i);
            }
        }
        return null;
    }


    @Override
    public void put(TileStackObject handle) {
        tiles.add(handle);
    }

    @Override
    public void close() {
        tiles.close();
    }

    @Override
    public void reDownloadTiles(ServiceContext sc) {
        for (int i = 0; i<tiles.size(); i++) {
            tiles.get(i).reDownload(sc);
        }
    }

    @Override
    public void reset() {
        tiles.reset();
    }

    @Override
    public void setCapacity(int capacity) {
        tiles.ensureCapacity(capacity);
    }
}
