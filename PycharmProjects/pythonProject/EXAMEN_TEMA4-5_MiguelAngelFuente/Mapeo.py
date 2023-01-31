from sqlalchemy.orm import declarative_base, relationship, sessionmaker
from sqlalchemy import Integer, Column, String, ForeignKey, PrimaryKeyConstraint, create_engine




Base = declarative_base()

# CREAR LA CLASE ALUMNOS******************************************************************
class alumnos(Base):
    __tablename__ = 'alumnos'
    DNI = Column(Integer, primary_key=True)
    APENOM = Column(String)
    POBLA = Column(String)
    TELEF = Column(String)

# CREAR LA CLASE ASIGNATURA******************************************************************
class asignaturas(Base):
    __tablename__ = 'asignaturas'
    COD = Column(Integer, primary_key=True)
    NOMBRE = Column(String)
    ABREVIATURA = Column(String)

# CREAR LA CLASE NOTAS******************************************************************
class notas(Base):
    __tablename__ = 'notas'

    DNI = Column(Integer, ForeignKey(alumnos.DNI))
    alumno = relationship("alumnos", back_populates="notas")

    COD = Column(Integer, ForeignKey(asignaturas.COD))
    asignatura = relationship("asignaturas", back_populates="notas")

    __table_args__ = (
        PrimaryKeyConstraint('DNI', 'COD'),
        {},
    )
    NOTA = Column(Integer)

# RELACIONES**************************************************************************************

#----RELACIONES NOTAS
alumnos.notas = relationship("notas", back_populates="alumno")
asignaturas.notas = relationship("notas", back_populates="asignatura")

# # PRUEBAS MAPEO*****************************************************************************************
# engine = create_engine('mysql+pymysql://ex2:adat@172.20.132.130/examen2')
# Session = sessionmaker(bind=engine)
# sesion = Session()
# lista = sesion.query(alumnos).all()
#
# for e in lista:
#     for n in e.notas:
#      print(e.APENOM,"-----",n.NOTA,"--------",n.asignatura.NOMBRE)
# sesion.close()