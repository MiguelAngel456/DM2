import os
import shutil
import csv


class Ejercicio2:

    def menu(self):
        num = (int)(input("""Hola, ¿qué quieres hacer hoy?
        1-Generar fichero csv de olimpiadas
        2-Buscar deportista
        3-Buscar deportistas por deporte y olimpiada
        4-Añadir deportista
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
        # Generar fichero csv de olimpiadas
        olimpiadas = []
        with open("athlete_events.csv") as csvfile:
            reader = csv.DictReader(csvfile)
            with open("olimpiadas.csv", "w") as csvOlimpiadas:  # Abre el archivo csv y escribe
                # Escribe las columnas del encabezado
                columnas = ["Games", "Year", "Season", "City"]
                writer = csv.DictWriter(csvOlimpiadas, columnas)
                writer.writeheader()
                # Escribe lo que lee
                for row in reader:
                    if not row["Games"] in olimpiadas:
                        olimpiadas.append(row["Games"])
                        writer.writerow(
                            {columnas[0]: row["Games"], columnas[1]: row["Year"], columnas[2]: row["Season"],
                             columnas[3]: row["City"]})
        self.menu()

    def metodo2(self):
        # Buscar deportista
        atleta = []
        persona = input("Nombre del deportista")
        with open("athlete_events.csv") as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if persona in row["Name"]:  # Comprueba las personas que tienen
                    if not row["Name"] in atleta:
                        atleta.append(row["Name"])
                        print(row["Name"], row["Sex"])
                    print(
                        "\t" + row['Games'] + row['Year'] + row['Season'] + row['City'] + row['Sport'] + row['Event'] +
                        row['Medal'])
            if len(atleta) == 0:
                print("No he encontrado a " + persona)
        self.menu()

    def metodo3(self):
        # Buscar deportistas por deporte y olimpiada
        cont = 0


        # deporte = input("Tipo de deporte")
        # año = input("Año")
        # temporada = input("Summer or Winter")

        deporte = "Basketball"
        año = "1992"
        temporada = "Summer"

        if temporada != "Summer" and temporada != "Winter":
            print("ERROR EN TEMPORADA")

        with open("athlete_events.csv") as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if deporte == row["Sport"] and año == row["Year"] and temporada == row["Season"]:
                    if cont == 0:
                        print(row['Games'] + " " + row['City'] + " " + row['Sport'])
                        cont += 1;
                    print("\t" + row['Name'] + "   " + row['Event'] + "   " + row['Medal'])
            if cont == 0:
                print("No se ha encontrado ningun deportista :(")

            # with open("athlete_events.csv", 'r') as f:
            #    last_line = f.readlines()[-1]
            #    print(last_line["ID"])

            # print(last_line)
        self.menu()

    def metodo4(self):
        #Añadir deportista
        olimpiadas = []

        with open("athlete_events.csv", "a") as csvOlimpiadas:
            columnas = ["ID", "Name", "Sex", "Age", "Height", "Weight", "Team", "NOC", "Games", "Year", "Season",
                        "City", "Sport", "Event", "Medal"]
            writer = csv.DictWriter(csvOlimpiadas, columnas)

            writer.writerow(
                {columnas[0]: "100000000000", columnas[1]: "Adrian Llahe", columnas[2]: "M", columnas[3]: "22",
                 columnas[4]: "170", columnas[5]: "35", columnas[6]: "España", columnas[7]: "ESP",
                 columnas[8]: "Vitoria 2022", columnas[9]: "2022", columnas[10]: "Summer", columnas[11]: "Vitoria",
                 columnas[12]: "Lanzamiento de piedras", columnas[13]: "Lanzamiento de piedras M",
                 columnas[14]: "GOOOLD"})
        self.menu()


e = Ejercicio2()
e.menu()
