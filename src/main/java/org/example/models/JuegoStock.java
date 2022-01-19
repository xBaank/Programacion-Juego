package org.example.models;

public class JuegoStock extends Juego{
    private int stockCount;

    public JuegoStock(Juego juego,int count) {
        super(juego.getTitle(),juego.getConsole(), juego.getPrice());
        stockCount = count;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public void removeStockCount(int stockCount) {
        this.stockCount -= stockCount;
    }

    public void addStockCount(int stockCount) {
        this.stockCount += stockCount;
    }
}
