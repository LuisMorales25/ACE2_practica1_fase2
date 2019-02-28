drop table Pronostico;

create table Pronostico (
	id					int not null IDENTITY(1,1),
	monoxido_carbono	float,
	aire				float,
	radiacion_solar		float,
	fecha				date,
	hora				time,
	latitud				float,
	longitud			float,
	constraint PK_Pronostico primary key (id)
);