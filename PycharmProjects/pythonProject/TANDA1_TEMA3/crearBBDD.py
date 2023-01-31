import mysql.connector

conDB=mysql.connector.connect(host="localhost",user="admin",password="password")
cur=conDB.cursor()
with open("olimpiadas.sql") as f:
    sql=f.read()

resultados=cur.execute(sql,multi=True)
try:
    for r in resultados:
      print(r)

except Exception as e:
    print("FIN")

