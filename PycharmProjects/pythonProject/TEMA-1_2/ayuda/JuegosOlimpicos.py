class JuegosOlimpicos:
    def __init__(self,ano,juegos,temporada,ciudad):
        self.ano = ano
        self.juegos = juegos
        self.temporada = temporada
        self.ciudad = ciudad

    def mostrarJuegos(self):
        print(self.ano +" "+self.juegos+" "+self.temporada+" "+self.ciudad)