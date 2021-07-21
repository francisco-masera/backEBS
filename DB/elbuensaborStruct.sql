create table config
(
    idConfig          bigint auto_increment
        primary key,
    cantidadCocineros int          not null,
    emailEmpresa      varchar(255) not null
);

create table informacionarticuloventa
(
    idArticuloVenta bigint auto_increment
        primary key,
    descripcion     varchar(255) not null,
    imagen          varchar(255) not null,
    precioVenta     float        not null
);

create table historialventas
(
    idHistorial bigint auto_increment
        primary key,
    costo       float  not null,
    fechaVenta  date   null,
    precioVenta float  not null,
    idArticulo  bigint not null,
    constraint FKiqpk8sh4anogq7cbvmnmvswxi
        foreign key (idArticulo) references informacionarticuloventa (idArticuloVenta)
);

create table persona
(
    idPersona   bigint auto_increment
        primary key,
    apellido    varchar(255)         not null,
    baja        tinyint(1) default 0 not null,
    contrasenia varchar(255)         not null,
    email       varchar(255)         not null,
    foto        varchar(255)         not null,
    nombre      varchar(255)         not null,
    telefono    varchar(255)         not null,
    usuario     varchar(255)         not null
);

create table cliente
(
    idCliente bigint not null
        primary key,
    token     int    null,
    constraint FKidh5cjaj6rwvd7ffi3f8xyeve
        foreign key (idCliente) references persona (idPersona)
);

create table Pedido
(
    idPedido    bigint auto_increment
        primary key,
    estado      varchar(255) not null,
    hora        time         null,
    numero      bigint       not null,
    tipoEntrega bit          not null,
    idCliente   bigint       not null,
    formaPago   bit          not null,
    constraint FKaxpy7jnkxyiemmhwpryyksqmd
        foreign key (idCliente) references cliente (idCliente)
);

create table DetallePedido
(
    idDetalle  bigint auto_increment
        primary key,
    cantidad   int    not null,
    idArticulo bigint not null,
    idPedido   bigint not null,
    constraint FKcu5cmqwe950m98ffijdd759wr
        foreign key (idPedido) references Pedido (idPedido),
    constraint FKgblnghlb028vohg62ha0fb5xd
        foreign key (idArticulo) references informacionarticuloventa (idArticuloVenta)
);

create table Factura
(
    idFactura           bigint auto_increment
        primary key,
    fechaHora           datetime null,
    formaPago           bit      not null,
    numero              bigint   not null,
    porcentajeDescuento float    not null,
    total               double   not null,
    idPedido            bigint   not null,
    constraint UK_c1jtshpjhg01detpak886jv5i
        unique (numero),
    constraint FKly6sa5ad8ua8p5pyyc1rxirq1
        foreign key (idPedido) references Pedido (idPedido)
);

create table TarjetaDebito
(
    idTarjeta     bigint auto_increment
        primary key,
    baja          tinyint(1) default 0 not null,
    nombreTitular varchar(255)         not null,
    numero        bigint               not null,
    vecimiento    date                 null,
    idCliente     bigint               not null,
    constraint UK_4mvihldyb4ogi4uqtdyvgwaht
        unique (numero),
    constraint FK5wsu9jc73rxhb2msbco5dc86n
        foreign key (idCliente) references cliente (idCliente)
);

create table domicilio
(
    idDomicilio  bigint auto_increment
        primary key,
    baja         tinyint(1) default 0 not null,
    calle        varchar(255)         not null,
    departamento varchar(255)         not null,
    localidad    varchar(255)         not null,
    numero       int                  not null,
    piso         int                  not null,
    idPersona    bigint               not null,
    latitud      varchar(255)         not null,
    longitud     varchar(255)         not null,
    constraint FKqskjqmjbcu4wcn120ovey9hm3
        foreign key (idPersona) references persona (idPersona)
);

create table empleado
(
    rol        varchar(255) not null,
    idEmpleado bigint       not null
        primary key,
    constraint FKpnt4sbao6fyenlefl82l95v5r
        foreign key (idEmpleado) references persona (idPersona)
);

create table rubroinsumo
(
    idRubroInsumo bigint       not null
        primary key,
    denominacion  varchar(255) not null
);

create table rubromanufacturado
(
    idRubroManufacturado bigint auto_increment
        primary key,
    baja                 tinyint(1) default 0 not null,
    denominacion         varchar(255)         not null
);

create table articulomanufacturado
(
    aptoCeliaco             bit                  not null,
    baja                    tinyint(1) default 0 not null,
    denominacion            varchar(255)         not null,
    tiempoCocina            int                  not null,
    vegano                  bit                  not null,
    vegetariano             bit                  not null,
    idArticuloManufacturado bigint               not null
        primary key,
    idRubro                 bigint               not null,
    constraint FKmleihlxvcplpjv5e3wtmnepuy
        foreign key (idRubro) references rubromanufacturado (idRubroManufacturado),
    constraint FKtimme61neajt7a5i0lgf4rk20
        foreign key (idArticuloManufacturado) references informacionarticuloventa (idArticuloVenta)
);

create table stock
(
    idStock bigint auto_increment
        primary key,
    actual  float not null,
    maximo  float not null,
    minimo  float not null
);

create table insumo
(
    idInsumo     bigint auto_increment
        primary key,
    baja         tinyint(1) default 0 not null,
    denominacion varchar(255)         not null,
    esInsumo     bit                  not null,
    unidadMedida varchar(255)         not null,
    idRubro      bigint               not null,
    idStock      bigint               not null,
    constraint FKaul117ixxf3b3im6dmxaxsnts
        foreign key (idRubro) references rubroinsumo (idRubroInsumo),
    constraint FKhin7lpolmf7r2u3cx3wsvt1k5
        foreign key (idStock) references stock (idStock)
);

create table historialcompraaproveedores
(
    idCompra       bigint auto_increment
        primary key,
    cantidad       float    not null,
    fechaCompra    datetime not null,
    precioUnitario float    not null,
    idInsumo       bigint   not null,
    constraint FK1gmxti380q4ukilmuts8d4u33
        foreign key (idInsumo) references insumo (idInsumo)
);

create table informacionarticuloventa_insumo
(
    idInsumoVenta bigint not null
        primary key,
    idInsumo      bigint not null,
    constraint FK4pqenon9ruljy3s0o8ldbynve
        foreign key (idInsumoVenta) references informacionarticuloventa (idArticuloVenta),
    constraint FKgx7s77masrtaiq2sj70fg3d06
        foreign key (idInsumo) references insumo (idInsumo)
);

create table receta
(
    idReceta        bigint auto_increment
        primary key,
    baja            tinyint(1) default 0 not null,
    cantidadInsumo  float                not null,
    idInsumo        bigint               not null,
    idManufacturado bigint               not null,
    constraint FKlk4tqorrqitu489uoj0kdkggf
        foreign key (idManufacturado) references articulomanufacturado (idArticuloManufacturado),
    constraint FKp05k0p75y456y0nfrpaxwl8yy
        foreign key (idInsumo) references insumo (idInsumo)
);

create table sugerenciachef
(
    idSugerencia bigint auto_increment
        primary key,
    aptoCeliaco  bit          not null,
    denominacion varchar(255) not null,
    descripcion  varchar(255) not null,
    imagen       varchar(255) not null,
    tiempoCocina int          not null,
    vegano       bit          not null,
    vegetariano  bit          not null
);

create table recetasugerida
(
    idRecetaSugerida bigint auto_increment
        primary key,
    cantidadInsumo   float  not null,
    idInsumo         bigint not null,
    idSugerencia     bigint not null,
    constraint FK4octeds5a27vbbsimmljsi966
        foreign key (idInsumo) references insumo (idInsumo),
    constraint FKf6didu3nsm8cg4lfmkgw2hroy
        foreign key (idSugerencia) references sugerenciachef (idSugerencia)
);

create
    definer = root@localhost function SPLIT_STRING(str varchar(255), delim varchar(12), pos int) returns varchar(255)
    no sql
    RETURN REPLACE(
            SUBSTRING(
                    SUBSTRING_INDEX(str, delim, pos),
                    CHAR_LENGTH(
                            SUBSTRING_INDEX(str, delim, pos - 1)
                        ) + 1
                ),
            delim,
            ''
        );

create
    definer = root@localhost procedure getInsumosVentaByFiltro(IN params varchar(255))
BEGIN

    DROP TABLE IF EXISTS Resultados;
    CREATE TEMPORARY TABLE Resultados
    (
        idArticuloVenta bigint PRIMARY KEY not null,
        descripcion     varchar(255)       null,
        imagen          varchar(255)       null,
        precioVenta     float              null,
        denominacion    varchar(255)       null,
        idRubro         bigint             null,
        idStock         bigint             null,
        idInsumo        bigint             null
    );

    DROP TABLE IF EXISTS Terminos;
    CREATE TABLE Terminos
    (
        Termino VARCHAR(255)
    );
    INSERT INTO Terminos SELECT `SPLIT_STRING`(params, ' ', 1);
    INSERT INTO Resultados(idArticuloVenta,
                           descripcion,
                           imagen,
                           precioVenta,
                           denominacion,
                           idRubro,
                           idStock,
                           idInsumo)

    SELECT ia.idArticuloVenta,
           ia.descripcion,
           ia.imagen,
           ia.precioVenta,
           i.denominacion,
           i.idRubro,
           i.idStock,
           i.idInsumo

    FROM informacionarticuloventa ia
             INNER JOIN informacionarticuloventa_insumo ii ON ii.idInsumoVenta = ia.idArticuloVenta
             INNER JOIN insumo i ON i.idInsumo = ii.idInsumo
             INNER JOIN Stock s ON s.idStock = i.idStock
             INNER JOIN (SELECT Termino FROM Terminos) as t
             INNER JOIN rubroinsumo r ON i.idRubro = r.idRubroInsumo
    WHERE i.baja = 0
      AND i.esInsumo = 0
      AND s.Actual > 0
      AND (ia.descripcion LIKE CONCAT('%', t.Termino, '%')
        OR i.denominacion LIKE CONCAT('%', t.Termino, '%')
        OR r.denominacion LIKE CONCAT('%', t.Termino, '%'));

    SELECT *
    FROM Resultados;

    DROP TABLE IF EXISTS Terminos;
END;

create
    definer = root@localhost procedure getManufacturadosByFiltro(IN params varchar(255))
BEGIN

    DROP TABLE IF EXISTS Resultados;
    CREATE TEMPORARY TABLE Resultados
    (
        idArticuloVenta         bigint PRIMARY KEY not null,
        descripcion             varchar(255)       null,
        imagen                  varchar(255)       null,
        precioVenta             float              null,
        aptoCeliaco             bit(1)             null,
        denominacion            varchar(255)       null,
        tiempoCocina            int                null,
        vegano                  bit(1)             null,
        vegetariano             bit(1)             null,
        idArticuloManufacturado bigint             null,
        idRubro                 bigint             null,
        idInsumo                bigint             null,
        idStock                 bigint             null
    );

    DROP TABLE IF EXISTS Terminos;
    CREATE TABLE Terminos
    (
        Termino VARCHAR(255)
    );

    INSERT INTO Terminos SELECT `SPLIT_STRING`(params, ' ', 1);

    INSERT INTO Resultados(idArticuloVenta,
                           descripcion,
                           imagen,
                           precioVenta,
                           aptoCeliaco,
                           denominacion,
                           tiempoCocina,
                           vegano,
                           vegetariano,
                           idArticuloManufacturado,
                           idRubro)

    SELECT ia.idArticuloVenta,
           ia.descripcion,
           ia.imagen,
           ia.precioVenta,
           am.aptoCeliaco,
           am.denominacion,
           am.tiempoCocina,
           am.vegano,
           am.vegetariano,
           am.idArticuloManufacturado,
           am.idRubro

    FROM informacionarticuloventa ia
             INNER JOIN articulomanufacturado am ON ia.idArticuloVenta = am.idArticuloManufacturado
             INNER JOIN (SELECT Termino FROM Terminos) as t
             INNER JOIN rubromanufacturado r ON r.idRubroManufacturado = am.idRubro
    WHERE am.baja = 0
      AND (ia.descripcion LIKE CONCAT('%', t.Termino, '%')
        OR am.denominacion LIKE CONCAT('%', t.Termino, '%')
        OR r.denominacion LIKE CONCAT('%', t.Termino, '%'));

    SELECT *
    FROM Resultados;

    DROP TABLE IF EXISTS Terminos;
END;


