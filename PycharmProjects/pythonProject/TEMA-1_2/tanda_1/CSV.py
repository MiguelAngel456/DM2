import csv

class CSV:
    def mostrarAtletas(self):
        cont = 0
        olimpiadas = []
        with open("athlete_events.csv") as csvFile:
            reader= csv.DictReader(csvFile)
            for row in reader:

                if not row['Games'] in olimpiadas:
                    olimpiadas.append(row['Games'])
                    cont += 1
                    print(row['Games'])
            print(cont)

    def crearCsvOlimpiadas(self):
        olimpiadas = []
        with open("athlete_events.csv") as csvFile:
            reader= csv.DictReader(csvFile)
            with open("Olimpiadas.csv", 'w') as csvOlimpiadas:

                columnas = ["Games","Year"]
                writer = csv.DictWriter(csvOlimpiadas,columnas)
                writer.writeheader()
                for row in reader:
                    if not row['Games'] in olimpiadas:
                        olimpiadas.append(row['Games'])
                        writer.writerow({columnas[0]:row['Games'],columnas[1]:row['Year']})

c= CSV()
c.mostrarAtletas()
c.crearCsvOlimpiadas()