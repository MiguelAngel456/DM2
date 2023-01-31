import csv

class CSV:
    def mostrarAtletas(self):
        cont =0
        atleta = []
        with open("../athlete_events.csv") as csvfile:
            reader =csv.DictReader(csvfile)
            for row in reader:
                if not row["Games"] in atleta:
                    atleta.append(row['Games'])
                    print(row['Games'])
                    cont +=1
                    print(cont)
            print(cont)

    def creearCsvOlimpiadas(self):
        olimpiadas = []
        with open("../athlete_events.csv") as csvfile:
            reader = csv.DictReader(csvfile)
            with open("../olimpiadas.csv", "w") as csvOlimpiadas:
                columnas = ["Games", "Year"]
                writer = csv.DictWriter(csvOlimpiadas,columnas)
                writer.writeheader()
                for row in reader:
                    if not row["Games"] in olimpiadas:
                        olimpiadas.append(row["Games"])
                        writer.writerow({columnas[0]:row["Games"],columnas[1]:row["Year"]})




c = CSV();
c.mostrarAtletas()
c.creearCsvOlimpiadas()