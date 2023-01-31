import csv
import pickle
from builtins import print
from xml.dom import minidom
from xml.etree import ElementTree as ET

from Examen_Miguel_Angel_Fuente.Batalla import Batalla


class Examen_MiguelAngelF:
    def menu(self):
        opc=(int)(input("""
        1.-Busca Batallas por region
        2.-Crear XML batllas
        3.-Crear fichero binario de objetos
        4.-Eliminar batalla fic. Binario de objertos
        0.-Salir"""))
        if(opc==1):
            self.buscaBatallas()
            self.menu()
        elif(opc==2):
            self.generarBatallasXML()
            self.menu()
        elif(opc==3):
            self.generarBatallasBinario()
            self.menu()
        elif(opc==4):
            self.BorrarBatalla()
            self.menu()
        elif(opc==0):
            print("adios")
        else:
            self.menu()
    def buscaBatallas(self):
        region=input("Region de la que quieres ver las batallas")


        with open("battles.csv") as csvFile:
            reader = csv.DictReader(csvFile)
            cant=0 #CONTAR CUANTAS BATALLAS A TENIDO
            for row in reader:
                if region == row['region']:
                    cant+=1
                    Localizacion=row['location']
                    Nombre=row['name']
                    año=row['year']
                    atacante=row['attacker_king']
                    defensor=row['defender_king']
                    #COMPROBAR DE QUE EXISTEN REYES
                    if(atacante==""):
                        reyAta="NO KING"
                    else:
                        reyAta=row['attacker_king']
                    if(defensor==""):
                        reyDef="NO KING"
                    else:
                        reyDef=row['defender_king']
                    #COMPROBAR EL RESULTADO
                    resultado=row['attacker_outcome']
                    if(resultado=="win"):
                        result="Gano el atacante"
                    elif(resultado=="loss"):
                        result="Gana Defensor"
                    else:
                        result="No hya ni ganadores ni perdedores"

                    print("region-->"+region+"\nLocalizacion-->"+Localizacion+"\nNombre de la batalla-->"+Nombre+"\nAño-->"+año+"\nAtacante-->"+reyAta+"\ndefensor-->"+reyDef+"\nResultado-->"+result)
                    print("-------------------------------------------------------------")
            if(cant==0):
                print("NO HAY BATALLAS EN ESTA REGION")


    def generarBatallasXML(self):
        root = ET.Element("juego_tronos")  # CREAR LA RAIZ

        with open("battles.csv") as battles:
            reader = csv.DictReader(battles)

            for row in reader:
                batalla=ET.Element("batalla")
                batalla.set("id",row['battle_number'])

                nombre=ET.SubElement(batalla,"nombre")
                nombre.text=row['name']
                anio=ET.SubElement(batalla,"anio")
                anio.text=row['year']
                region=ET.SubElement(batalla,"region")
                region.text=row['region']

                #COMPROBAR QUE TIENE LOCALIZACION
                localizacion=ET.SubElement(batalla,"localizacion")
                if(row['location']==""):
                    localizacion.text="no place"
                else:
                    localizacion.text=row['location']
                root.append(batalla)
                #ATACANTES
                rootAtaque=ET.SubElement(batalla,"ataque")
                rootAtaque.set("tamanio",row['attacker_size'])

                rootAtaque.set("gana", row['attacker_outcome'])
                # COMPROBAR QUE TIENE REYES
                reyAtac=ET.SubElement(rootAtaque,"rey")
                if(row['attacker_king']==""):
                    reyAtac.text = "NO KING"
                else:
                    reyAtac.text=row['attacker_king']

                #COMPROBAR QUE HAY COMANDANTE
                coman=ET.SubElement(rootAtaque,"comandante")
                if(row['attacker_commander']==""):
                    coman.text="NO COMANDANTE"
                else:
                    coman.text=row['attacker_commander']

                #COMPROBAR LAS FAMILIAS
                #fam1=ET.SubElement(rootAtaque,"familia")

                #DEFENSORES
                rootDefen=ET.SubElement(batalla,"defensa")
                rootDefen.set("tamanio", row['defender_size'])
                if(row['attacker_outcome']=="loss"):
                    rootDefen.set("gana", "S")
                else:
                    rootDefen.set("gana", "N")
                # COMPROBAR QUE TIENE REYES
                reyDef = ET.SubElement(rootDefen, "rey")
                if (row['defender_king'] == ""):
                    reyAtac.text = "NO KING"
                else:
                    reyAtac.text = row['defender_king']
               # COMPROBAR QUE HAY COMANDANTE
                comanDef = ET.SubElement(rootDefen, "comandante")
                if (row['defender_commander'] == ""):
                    coman.text = "NO COMANDANTE"
                else:
                    coman.text = row['defender_commander']
            str2 = minidom.parseString(ET.tostring(root)).toprettyxml(indent="\t")
            print (str)
            f = open("battles.xml", "w")
            f.write(str2)
            f.close()

    def generarBatallasBinario(self):
        with open("battles.bin", "wb") as f:
            raiz = ET.parse("battles.xml").getroot()
            lista = raiz.findall("batalla")

            for al in lista:
                id=al.get("id")
                nombre=al.find("nombre").text
                anio=al.find("anio").text
                region=al.find("region").text
                localizacion=al.find("localizacion").text
                reyA=al.find("ataque").find("rey").text
                reyD=al.find("defensa").find("rey").text
                gana=al.find("ataque").get("gana")
                bat=Batalla(id,nombre,anio,region,localizacion,reyA,reyD,gana)
                #print(bat.__str__())
                pickle.dump(bat, f)
    def borrarBatalla(self):
        idBorrar=input("Introduce el id de la batalla que desea Eliminar")
        confirmacion=input("Estas seguro de que lo quieres borrar  la siguiente batalla?")
        if(confirmacion=="si"):
            lista = []
            with open("battles.bin", "rb") as f:
                while True:
                    try:
                        ol = pickle.load(f)
                        if idBorrar != ol.id:
                            lista.append(ol)
                    except EOFError:
                        break
            with open("Olimpiadas.pickle", "wb") as f:
                for li in lista:
                    pickle.dump(li, f)

ex=Examen_MiguelAngelF()
ex.menu()