GO  
CREATE PROCEDURE InsertarPronostico
    @monoxido_carbono	varchar(25),
	@aire				varchar(25),
	@radiacion_solar	varchar(25),
	@fecha				date,
	@hora				time,
	@latitud			varchar(25),
	@longitud			varchar(25)  
AS   

    insert into Pronostico (monoxido_carbono, aire,
							radiacion_solar, fecha,
							hora, latitud, longitud)
	values (@monoxido_carbono, @aire, @radiacion_solar,
			@fecha, @hora, @latitud, @longitud);
GO  