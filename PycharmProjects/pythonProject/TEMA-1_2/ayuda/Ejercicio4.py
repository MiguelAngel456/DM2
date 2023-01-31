import xml.sax
import xml.etree.ElementTree as ET
import pickle



from JuegosOlimpicos import JuegosOlimpicos
class Ejercicio4:

    def menu(self):
        num = (int)(input("""Hola, ¿qué quieres hacer hoy?
        1-Crear fichero serializable de olimpiadas
        2-Añadir edición olímpica
        3-Buscar olimpiadas por sede
        4-Eliminar edición olímpica
        5-Salir del programa

        """))

        if num == 1:
            self.metodo1()
        elif num == 2:
            self.metodo2()
        elif num == 3:
            self.metodo3()
        elif num == 4:
            self.metodo4()
        else:
            print("Adio")

    def metodo1(self):
        #Crear fichero serializable de olimpiadas
        with open("olimpiadas.pickle", "wb") as f: # Escribe
            raiz = ET.parse("olimpiadas.xml").getroot() # Para "leer" el xml
            lista = raiz.findall("olimpiada")# Para leer todas las olimpiada
            for l in lista:
                year = l.get("year")# Coger el atributo
                juegos = l.find("juegos").text # Coger el Elemento
                temporada = l.find("temporada").text
                ciudad = l.find("ciudad").text
                juegosOlim = JuegosOlimpicos(year,juegos,temporada,ciudad) #Crear un objeto
                pickle.dump(juegosOlim, f)#Escribe el objeto en el fichero
        self.leer()
        self.menu()


    def metodo2(self):

        year = input("Añade year")
        juegos = input("Añade juegos")
        temporada = input("Añade temporada")
        ciudad = input("Añade ciudad")
        juegosOlim = JuegosOlimpicos(year, juegos, temporada, ciudad)

        list = []
        anadido = False
        with open("olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    olimpiada = pickle.load(f)
                    if olimpiada.ano > year and anadido == False:
                        anadido = True
                        list.append(juegosOlim)
                    list.append(olimpiada)
                except EOFError:
                    break
            if anadido==False:
                list.append(juegosOlim)

        # list = sorted(list, key=lambda x: x.temporada, reverse=True)
        # list = sorted(list, key=lambda x: x.year)

        with open("olimpiadas.pickle", "wb") as f:
            for l in list:
                pickle.dump(l, f)
        self.leer()
        self.menu()


    def metodo3(self):
        edicion = input("Introduce la sede de la olimpiada")
        lista = []
        with open("olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    olimpiada = pickle.load(f)
                    if edicion in olimpiada.ciudad:
                        lista.append(olimpiada)
                except EOFError:
                    break
            for l in lista:
                l.mostrarJuegos()
        self.menu()

    def metodo4(self):
        ano = input("Introduce el año")
        temporada = input("Introduce la temporada")

        listaolimp = []
        verificar = False
        with open("olimpiadas.pickle", "rb")as f:
            while True:
                try:
                    listaolimp.append(pickle.load(f))
                except EOFError:
                    break
        with open("olimpiadas.pickle", "wb") as f:
            for olim in listaolimp:
                if ano != olim.ano or temporada != olim.temporada:
                    pickle.dump(olim, f)
                else:
                    verificar = True

        if verificar == False:
            print("No existe olimpada con el año " + ano + " en temporada " + temporada)
        else:
            self.leer()
        self.menu()

    def leer(self):
        with open("olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    juegos = pickle.load(f)
                    juegos.mostrarJuegos()
                except EOFError:
                    break

e = Ejercicio4()
e.menu()