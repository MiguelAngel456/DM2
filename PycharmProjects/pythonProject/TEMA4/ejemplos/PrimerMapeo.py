
from sqlalchemy import Integer, Column, String, create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# CREAR MOTOR*********************************************************************
engine = create_engine("sqlite:///olimpiadas.db")#echo = True

# CREAR SESSION*******************************************************************
Session = sessionmaker(bind=engine)
session=Session()
Base = declarative_base()

# CREAR LA CLASE******************************************************************
class Deporte(Base):
   __tablename__ = 'Deporte'
   id_deporte = Column(Integer, primary_key = True)
   nombre = Column(String)

# LISTAR**************************************************************************
resultado = session.query(Deporte).all()
for r in resultado:
    print(r.id_deporte,"       ",r.nombre)
print("\nBUSCAR")
# BURCAR UNO DE DIFERENTES MANERAS************************************************
print(session.query(Deporte).first().nombre)
print(session.query(Deporte).get(11).nombre)
resultado = session.query(Deporte).filter(Deporte.nombre == "Basketball")
for r in resultado:
    print(r.id_deporte,"       ",r.nombre)
deporte = Deporte()
print("\nAÑADIR")
# AÑADIR**************************************************************************
deporte.nombre = "Rugby"
session.add(deporte)
resultado = session.query(Deporte).all()
for r in resultado:
    print(r.id_deporte,"       ",r.nombre)

session.commit()
print("\nMODIFICAR")
# MODIFICAR**************************************************************************
deporte = session.query(Deporte).filter(Deporte.nombre == "Rugby")
deporte.nombre="Rugby7"
for dep in deporte:
    dep.nombre = "Rugby7"
session.commit()
resultado = session.query(Deporte).all()
for r in resultado:
    print(r.id_deporte,"       ",r.nombre)


print("\nELIMINAR")
# ELIMINAR**************************************************************************
deporte = session.query(Deporte).filter(Deporte.nombre == "Rugby7")
deporte.delete()
resultado = session.query(Deporte).all()
for r in resultado:
    print(r.id_deporte,"       ",r.nombre)
session.commit()


session.close()