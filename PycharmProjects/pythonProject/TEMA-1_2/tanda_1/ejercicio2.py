import csv

class ejercicio2:
#FICHERO CSV

    def menu(self):
        opc=(int)(input("""Elige una opcion
        1.-Generar fichero de olimpiadas
        2.-Buscar deportistas
        3.-Buscar deportistas por deporte y olimpiada
        4.-Añadir Deportista"""))
        if opc==1:
            self.opc1()
        elif opc==2:
            self.opc2()
        elif opc == 3:
            self.opc3()
        elif opc == 4:
            self.opc4()

            #W--> SOBRESCRIBE|| AB--> CONTINUA LA ESCRITURA

    def opc1(self):
        olimpiadas=[]
        with open("athlete_events.csv") as csvFile:
            reader= csv.DictReader(csvFile)
            with open("Olimpiadas.csv", 'w') as Olimpiadas:
                columnas = ["Games","Year","Season","City"]
                writer = csv.DictWriter(Olimpiadas, columnas)
                writer.writeheader()
                for row in reader:
                    for row in reader:
                        if not row['Games'] in olimpiadas:
                            olimpiadas.append(row['Games'])
                            writer.writerow({columnas[0]: row['Games'], columnas[1]: row['Year'], columnas[2]: row['Season'], columnas[3]: row['City']})

    #BUSCAR
    def opc2(self):
        nombre = input("Nombre del deportista a buscar")
        cont = 0
        athleta = []
        with open("athlete_events.csv") as csvFile:
            reader = csv.DictReader(csvFile)
            for row in reader:

                if nombre in row['Name'] :
                    if not row['Name'] in athleta:
                        athleta.append(row['Name'])
                        print(row['Name'] + " " + row['Sex'] + " " + row['Age'])
                    print("\tparticipacion :"+row['Games']+" "+row['Sport']+" "+row['Event'])

            if len(athleta)==0:
                print("No se a encontrar a ningún deportista")




    #*En el tercer punto con in en vez de == no se
    # filtran bien los campos y no preguntes en
    # cada línea si hay que poner la cabecera

    def opc3(self):
        deporte=input("¿Deporte que quieres consultar?")
        año=input("¿año que quieres consultar?")
        temporada=input("¿Que temporada quieres consultar?")
        listdep=[]
        with open("athlete_events.csv") as csvFile:
            reader = csv.DictReader(csvFile)

            for row in reader:
                if deporte == row['Sport'] and año == row['Year'] and temporada == row['Season']:
                    if not row['Sport'] in listdep:
                        listdep.append(row['Sport'])
                        print(row['Games'] + " " + row['City'] + " " + row['Sport'])
                    print("\tParticiparon:"+row['Name']+" "+row['Event']+" "+row['Medal'])
            if len(listdep)==0:
                print("No se a encontrar a ningún deportista")
    def opc4(self):
       # nombre=input("Nombre del deportista:")
       # sex=input("Sexo del deportista:")
       # edad=input("Edad del deportista:")
       # altura=input("Altura del deportista:")
       # peso=input("Peso del deportista:")
       # equipo=input("Equipo del deportista:")
       # NOC=input("NOC:")
       # juego=input("Juego:")
       # año=input("año:")
       # temporada=input("Temporada:")
       # ciudad=input("Ciudad:")
       # deporte=input("Deporte:")
       # evento=input("evento:")
       # medalla=input("medalla:")

        columnas = ["ID","Name","Sex","Age","Height","Weight","Team","NOC","Games","Year","Season","City","Sport","Event","Medal"]

        with open("athlete_events.csv", 'ab') as csvFile:
            writer = csv.DictWriter("athlete_events.csv")
            writer.writerow({columnas[0]: "135572",columnas[1]: "Miguel",columnas[2]: 'M',columnas[3]: "22",columnas[4]: 185,columnas[5]: 80,columnas[6]: "España",columnas[7]: "ESP",columnas[8]: "Futbol",columnas[9]: "2022", columnas[10]: "Winter",columnas[11]: "Vitoria",columnas[12]: "Futbol", columnas[13]: "Liga", columnas[14]: "Gold"})

ej = ejercicio2()
ej.menu()