import pickle
from xml.etree import cElementTree as ET
#FICHEROS BINARIOS


from tanda_1.Olimpiada import Olimpiada
class Ejercicio4:

    def menu(self):
        menu = (int)(input("""Elige una opcion:
        1.-Crear fichero serializable de olimpiadas
        2.-Añadir edición olímpica
        3.-Buscar olimpiadas por sede
        4.-Eliminar edición olímpica"""))
        if menu==1:
            self.opc1()
        if menu==2:
            self.opc2()
        if menu==3:
            self.opc3()
        if menu==4:
            self.opc4()
    #CREAR Y RELLENAR
    def opc1(self):
        with open("Olimpiadas.pickle", "wb") as f:
            raiz = ET.parse("olimpiada.xml").getroot()
            lista = raiz.findall("Olimpiada")

            for al in lista:
                ano = al.get("Year")
                juego = al.find("juegos").text
                temporada = al.find("Temporada").text
                Ciudad= al.find("Ciudad").text
                ol=Olimpiada(ano,juego,temporada,Ciudad)
                pickle.dump(ol, f)
    #LEER
    def leer(self):

        with open("Olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    ol = pickle.load(f)
                    ol.verOlimpiada()
                except EOFError:
                    break
    #INSERTAR
    def opc2(self):
        ano=input("Introduce el año")
        juego=input("Introduce el juego")
        temp=input("Introduce la Temporada")
        ciu=input("Introduce la Ciudad")

        ol = Olimpiada(ano, juego, temp,ciu)
        lista=[]
        # ORDENAR MANUALMENTE
        # with open("Olimpiadas.pickle", "rb") as f:
        #     while True:
        #         try:
        #             ol = pickle.load(f)
        #             if ol.ano <= ol1.ano:
        #                 if ol1.ano < :
        #                     lista.append(ol1)
        #             lista.append(ol)
        #         except EOFError:
        #             break


       # SIN ORDENAR
        with open("Olimpiadas.pickle", "ab") as f:
            pickle.dump(ol, f)

    def opc3(self):
        ciu = input("Introduce la Ciudad")
        with open("Olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    ol = pickle.load(f)
                    if ciu in ol.ciudad:
                        ol.verOlimpiada()
                except EOFError:
                    break
    def opc4(self):
        ano = input("Introduce el año")
        temp = input("Introduce la Temporada")
        lista=[]
        with open("Olimpiadas.pickle", "rb") as f:
            while True:
                try:
                    ol = pickle.load(f)
                    if ano != ol.ciudad:
                        if temp != ol.temporada:
                            lista.append(ol)
                except EOFError:
                    break
        with open("Olimpiadas.pickle", "wb") as f:
            for li in lista:
                pickle.dump(li,f)


ej4=Ejercicio4()
ej4.leer()
print("-----------")
print("-----------")
ej4.menu()

