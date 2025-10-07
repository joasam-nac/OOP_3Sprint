package uppgift2;

public class Bensinräknare {
    private int nyMätarställning, gammalMätarställning;
    private double antalLiter;

    public Bensinräknare(int nyMätarställning, int gammalMätarställning, int antalLiter) {
        this.nyMätarställning = nyMätarställning;
        this.gammalMätarställning = gammalMätarställning;
        this.antalLiter = antalLiter;
    }

    public Bensinräknare(){
        this.nyMätarställning = 0;
        this.gammalMätarställning = 0;
        this.antalLiter = 0;
    }

    public void setNyMätarställning(int nyttVärde){
        this.nyMätarställning = nyttVärde;
    }

    public void setGammalMätarställning(int nyttVärde){
        this.gammalMätarställning = nyttVärde;
    }

    public void setAntalLiter(int nyttVärde){
        this.antalLiter = nyttVärde;
    }

    public int getAntalMil(){
        return nyMätarställning - gammalMätarställning;
    }

    public double getAntalLiter(){
        return antalLiter;
    }

    public double getFörbrukingPerMil(){
        return getAntalLiter() / getAntalMil();
    }
}
