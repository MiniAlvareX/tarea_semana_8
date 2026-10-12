public class Personaje { 

    private String nombre; 

    private int vida, ataque, defensa, alcance; 

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) { 

        this.nombre = nombre; 

        this.vida = vida; 

        this.ataque = ataque; 

        this.defensa = defensa; 

        this.alcance = alcance; 

    } 

    public String getNombre() { 

        return nombre; 

    } 

    public int getVida() { 

        return vida; 

    } 

    public int getAtaque() { 

        return ataque; 

    } 

    public int getDefensa() { 

        return defensa; 

    } 

    public int getAlcance() { 

        return alcance; 

    } 

    public void setVida(int vida) { 

        if (vida > 0) this.vida = vida; 

    } 

    public void setAtaque(int ataque) { 

        if (ataque > 0) this.ataque = ataque; 

    } 

    public void setDefensa(int defensa) { 

        if (defensa > 0) this.defensa = defensa; 

    } 

    public void setAlcance(int alcance) { 

        if (alcance > 0) this.alcance = alcance; 

    } 

    @Override 

    public String toString() { 

        return nombre + " | Vida: " + vida 

                + " | Ataque: " + ataque 

                + " | Defensa: " + defensa 

                + " | Alcance: " + alcance; 

    } 

} 
