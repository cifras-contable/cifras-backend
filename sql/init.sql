USE cifras;

CREATE TABLE grupo_de_empresas
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_grupo VARCHAR(100) NOT NULL
);


CREATE TABLE empresa
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    id_grupo           INT         NOT NULL,
    cuit               VARCHAR(20) NOT NULL,
    razon_social       VARCHAR(100),
    nombre_de_fantasia VARCHAR(100),
    FOREIGN KEY (id_grupo) REFERENCES grupo_de_empresas (id)
);



CREATE TABLE usuario
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    id_viejo            INT,
    nombre_apellido     VARCHAR(100) NOT NULL,
    id_grupo            INT null,
    master              BIT,
    administrador       BIT,
    dni                 INT,
    credencial_login    VARCHAR(30),
    credencial_password VARCHAR(20),
    desabilitado        BIT,
    FOREIGN KEY (id_grupo) REFERENCES grupo_de_empresas (id)
);


CREATE TABLE zona_de_rendicion
(
    id_empresa        INT,
    id                INT AUTO_INCREMENT PRIMARY KEY,
    id_viejo          VARCHAR(10),
    nombre_de_la_zona VARCHAR(100),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id)
);


CREATE TABLE grupo_de_permisos
(
    id_empresa       INT,
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nombre_del_grupo VARCHAR(100),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id)
);



CREATE TABLE usuario_empresa
(
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario                 INT,
    id_empresa                 INT,
    area_contable              SMALLINT DEFAULT 99,
    id_zona_de_rendicion       INT,
    punto_de_venta             INT,
    punto_de_venta_restringido BIT,
    perm_modif_parcial         BIT,
    acc_a_otras_zonas_rend     BIT,
    auditor_cajas              BIT,
    acc_cuentas_confidencial   BIT,
    id_grupo_de_permisos       INT,
    FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    FOREIGN KEY (id_zona_de_rendicion) REFERENCES zona_de_rendicion (id),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_grupo_de_permisos) REFERENCES grupo_de_permisos (id)
);



CREATE TABLE opciones_del_sistema
(
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    nombre_de_la_opcion      VARCHAR(300),
    identificacion_de_acceso VARCHAR(20)
);



CREATE TABLE permisos_de_acceso
(
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    id_grupo_de_permisos    INT,
    id_opciones_del_sistema INT,
    FOREIGN KEY (id_grupo_de_permisos) REFERENCES grupo_de_permisos (id),
    FOREIGN KEY (id_opciones_del_sistema) REFERENCES opciones_del_sistema (id)
);



CREATE TABLE tipos_de_transacciones
(
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    origen_de_la_transaccion VARCHAR(100)
);



CREATE TABLE tipos_de_transacciones_tablas
(
    id_tipo_de_transaccion         INT,
    nombre_de_la_tabla_involucrada VARCHAR(100),
    FOREIGN KEY (id_tipo_de_transaccion) REFERENCES tipos_de_transacciones (id)
);


CREATE TABLE transacciones
(
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa             INT,
    id_viejo               BIGINT,
    id_tipo_de_transaccion INT,
    id_usuario_que_crea    INT NULL,
    fecha                  DATETIME,
    detalle_modificaciones VARCHAR(256),
	detalle_eliminaciones		VARCHAR(256),
	id_usuario_que_elimina		INT NULL,
	fecha_eliminacion			DATETIME,
	FOREIGN KEY (id_empresa)				REFERENCES empresa(id),
	FOREIGN KEY (id_usuario_que_crea)		REFERENCES usuario(id),
	FOREIGN KEY (id_usuario_que_elimina)	REFERENCES usuario(id),
	FOREIGN KEY (id_tipo_de_transaccion)	REFERENCES tipos_de_transacciones(id)
);



CREATE TABLE modelos_de_comprobantes
(
    id_empresa                INT,
    id                        INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                     INT,

    nombre_del_archivo        VARCHAR(100),
    descripcion_del_documento VARCHAR(100),
    se_puede_editar           BIT,
    letra_del_documento       VARCHAR(1),
    tipo_de_documento         VARCHAR(30),
    se_usa_este_por_defecto   BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);


CREATE TABLE puntos_de_venta
(
    id_empresa         INT,
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    id_nt              INT,
    en_uso             BIT,
    numero             INT,
    nombre             VARCHAR(40),
    sistema            SMALLINT,
    mercado            VARCHAR(20),
    nombre_de_fantasia VARCHAR(80),
    ruta_del_logotipo  VARCHAR(200),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE afip_monedas
(
    id           VARCHAR(4) PRIMARY KEY,
    denominacion VARCHAR(100),
    cambio       DECIMAL(14, 4)
);


CREATE TABLE afip_paises
(
    id           VARCHAR(4) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_provincias
(
    id           VARCHAR(4) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_categoria_tributaria
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_iibb_jurisdicciones
(
    jurisdicciones       VARCHAR(4),
    provincia            VARCHAR(50),
    regimen              VARCHAR(4),
    retencion_percepcion VARCHAR(1),
    descripcion          VARCHAR(300)
);



CREATE TABLE afip_escalas_gan
(
    regimen                DECIMAL(7, 1),
    porcentaje_inscripto   DECIMAL(8, 2),
    porcentaje_noinscripto DECIMAL(7, 2),
    importe_no_sujeto      DECIMAL(14, 2)
);


CREATE TABLE afip_escalas_gan2
(
    desde      DECIMAL(14, 2),
    hasta      DECIMAL(14, 2),
    importe    DECIMAL(14, 2),
    porcentaje DECIMAL(7, 2)
);


CREATE TABLE afip_regimen_ganancias
(
    regimen     DECIMAL(7, 1) PRIMARY KEY,
    descripcion VARCHAR(300)
);



CREATE TABLE afip_alicuotas
(
    codigo       VARCHAR(4) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_cuit_pais
(
    codigo       VARCHAR(20) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_tipos_de_comprobantes_de_compra
(
    codigo                VARCHAR(3) PRIMARY KEY,
    denominacion          VARCHAR(200),
    tipo_de_comprobante   VARCHAR(2),
    letra_del_comprobante VARCHAR(1),
    adminte_solo_cuit     BIT,
    se_usa_habitualmente  BIT,
    tipo_de_saldo         SMALLINT
);


CREATE TABLE afip_tipos_de_comprobantes_de_venta
(
    codigo                VARCHAR(3) PRIMARY KEY,
    denominacion          VARCHAR(200),
    texto_a_imprimir      VARCHAR(200),
    tipo_de_comprobante   VARCHAR(2),
    letra_del_comprobante VARCHAR(1),
    se_usa_habitualmente  BIT,
    tipo_de_saldo         SMALLINT
);


CREATE TABLE afip_aduanas
(
    codigo       VARCHAR(3) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_codigo_operacion_compras
(
    codigo       VARCHAR(1) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_codigo_operacion_ventas
(
    codigo       VARCHAR(1) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_comprobantes_servicios
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_conceptos_de_compras_globales
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_conceptos_incluidos
(
    codigo       VARCHAR(1) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_exportaciones_destino
(
    codigo       VARCHAR(4) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_tipos_de_documentos
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(200)
);


CREATE TABLE afip_idiomas
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_incoterms
(
    codigo       VARCHAR(3) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_otros_tributos
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_unidades_de_medidas
(
    codigo       VARCHAR(2) PRIMARY KEY,
    denominacion VARCHAR(100)
);


CREATE TABLE afip_tipos_de_retenciones
(
    id VARCHAR(30) PRIMARY KEY
);
INSERT INTO afip_tipos_de_retenciones (id)
values ('GANANCIAS'),('INGRESOS BRUTOS '),('iva '),('SUSS '),('TASAS Y ESTADISTICAS '),('OTROS ');



CREATE TABLE plan_de_cuentas
(
    id_empresa                  INT,
    id                          INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                       INT,
    codigo_cuenta               BIGINT       NOT NULL,
    codigo_atajo                INT,
    descripcion                 VARCHAR(100) NOT NULL,
    cuenta_padre                int,
    cuenta_padre_id             INT REFERENCES plan_de_cuentas (id),
    recibe_asiento              BIT,
    cuenta_de_resultado         BIT,
    ajuste_por_inflacion        BIT,
    distribuye_por_centro_costo BIT,
    cuenta_confidencial         BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);


CREATE TABLE codigos_de_parametros_para_asientos_predefinidos
(
    codigo      VARCHAR(30) PRIMARY KEY,
    descripcion VARCHAR(100)
);
INSERT INTO codigos_de_parametros_para_asientos_predefinidos (codigo, descripcion)
values ('01-DV', 'Deudores por ventas'),
('01-PROV', 'Proveedores'),
('01-ANTDV', 'Anticio de Deudores por ventas'),
('01-ANTPROV', 'Anticipo de Proveedores'),
('02-EFEC', 'Efectivo'),
('02-CH3', 'Cheques de terceros o valores al cobro'),
('02-TRANFB', 'Transferencia Bancaria'),
('02-CHPROP', 'Emisi�n de cheques propios'),
('02-TJCOBR', 'Cobro con Tarjetas de cr�dito'),
('02-TJPAG', 'Pago con Tarjeta de Cr�dito'),
('03-MERC', 'Mercader�a'),
('03-GASTOS', 'Gastos a imputar'),
('04-IVACREDITO', 'IVA Cr�dito Fiscal'),
('04-IVADEDITO', 'IVA D�bito Fiscal'),
('04-RET_IVA_S', 'Retenciones de IVA sufridas'),
('04-RET_GAN_S', 'Retenciones de Ganancia sufridas'),
('04-RET_IB_S', 'Retenciones de Ingresos Brutos sufridas'),
('04-RET_SUSS_S', 'Retenciones de SUSS sufridas'),
('04-RET_IVA_P', 'Retenciones de IVA practicadas'),
('04-RET_GAN_P', 'Retenciones de Ganancia practicadas'),
('04-RET_IB_P', 'Retenciones de Ingresos Brutos practicadas'),
('04-RET_SUSS_P', 'Retenciones de SUSS practicadas'),
('04-PERC_IVA_S', 'Percepciones de IVA sufrida'),
('04-PERC_IB_S', 'Percepciones de Ingresos Brutos sufrida'),
('04-PERC_GAN_S', 'Percepciones de Ganancia sufrida'),
('04-PERC_SUSS_S', 'Percepciones de SUSS sufrida'),
('04-PERC_IVA_P', 'Percepciones de IVA practicada'),
('04-PERC_IB_P', 'Percepciones de Ingresos Brutos practicada'),
('04-PERC_GAN_P', 'Percepciones de Ganancia practicada'),
('04-PERC_SUSS_P', 'Percepciones de SUSS practicada'),
('04-RET_OTRAS', 'Otras Retenciones y Percepciones'),
('04-TYE', 'Tasas y Estad�sticas'),
('04-DOCUM', 'Documentos para ajustes de cta cte ventas /proveedor'),
('05-GBCO', 'Gastos bancarios');



CREATE TABLE parametros_para_asientos_predefinidos_por_empresa
(
    id_empresa         INT,
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    id_destino         VARCHAR(30),
    id_plan_de_cuentas INT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_destino) REFERENCES codigos_de_parametros_para_asientos_predefinidos (codigo),
    FOREIGN KEY (id_plan_de_cuentas) REFERENCES plan_de_cuentas (id)
);

CREATE TABLE ejercicio_contable
(
    id_empresa                INT,
    id                        INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                     INT,
    fecha_de_inicio_ejercicio DATE,
    fecha_de_fin_ejercicio    DATE,
    numero_de_ejercicio       INT,
    ejercicio_cerrado         BIT DEFAULT 0,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);


CREATE TABLE centros_de_costo
(
    id_empresa                 INT,
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                      INT,
    id_viejo                   VARCHAR(20),
    cento_de_costo_por_defecto BIT,
    nombre                     VARCHAR(100),
    desactivado                BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE registro_de_indices_historicos
(
    id_empresa     INT,
    tipo_de_indice VARCHAR(20),
    fecha          DATE,
    cambio         DECIMAL(14, 4),
    bache          BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id)
);



CREATE TABLE indices_para_el_ajuste_por_inflacion
(
    id_empresa INT,
    id_nt      INT,
    fecha      DATE,
    indice     DECIMAL(14, 5),
    factor     DECIMAL(14, 5),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE mayor_de_cuentas
(
    id_empresa            INT,
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                 INT,
    id_viejo              INT,
    fecha                 DATETIME,
    subdiario             VARCHAR(20),
    id_ejercicio_contable INT,
    area_contable         SMALLINT,
    total_debe            DECIMAL(14, 2),
    total_haber           DECIMAL(14, 2),
    concepto              VARCHAR(100),
    otros_detalles        VARCHAR(100),
    meses                 SMALLINT DEFAULT 1,
    offset                SMALLINT DEFAULT 0,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_ejercicio_contable) REFERENCES ejercicio_contable (id)
);



CREATE TABLE mayor_de_cuentas_detalle
(
    id_empresa          INT,
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    id_mayor_de_cuentas INT,
    id_plan_de_cuentas  INT,
    debe                DECIMAL(14, 2),
    haber               DECIMAL(14, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_mayor_de_cuentas) REFERENCES mayor_de_cuentas (id),
    FOREIGN KEY (id_plan_de_cuentas) REFERENCES plan_de_cuentas (id)
);


CREATE TABLE centro_de_costo_detalles
(
    id_empresa                  INT,
    id_mayor_de_cuentas_detalle INT,
    id_centros_de_costo         INT,
    debe                        DECIMAL(14, 2),
    haber                       DECIMAL(14, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_mayor_de_cuentas_detalle) REFERENCES mayor_de_cuentas_detalle (id),
    FOREIGN KEY (id_centros_de_costo) REFERENCES centros_de_costo (id)
);



CREATE TABLE rubros_de_articulos
(
    id_empresa             INT,
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                  INT,
    id_viejo               INT,
    descripcion            VARCHAR(100),
    visibles_desde_ventas  BIT,
    visibles_desde_compras BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE deposito_de_articulos
(
    id_empresa                       INT,
    id                               INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                            INT,
    id_viejo                         INT,
    nombre                           VARCHAR(100),
    domicilio                        VARCHAR(100),
    es_planta_de_produccion          BIT,
    tipo_de_remito_que_usa           SMALLINT,
    nombre_del_remito                VARCHAR(40),
    nombre_de_la_orden_de_carga      VARCHAR(40),
    ruta_de_impresion_remito         VARCHAR(200),
    ruta_de_impresion_orden_de_carga VARCHAR(200),
    usa_precintos                    BIT,
    es_un_deposito_para_despachos    BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);


CREATE TABLE vehiculos
(
    id_empresa           INT,
    id                   INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                INT,
    id_viejo             INT,
    interno              VARCHAR(40),
    codigo_sistema_sbi   VARCHAR(30),
    marca_del_vehiculo   VARCHAR(40),
    patente_del_vehiculo VARCHAR(30),
    tipo_de_vehiculo     VARCHAR(20),
    capacidad_en_m3      DECIMAL(2),
    m3_por_dia           DECIMAL(5),
    detalle              VARCHAR(100),
    propiedad            VARCHAR(20),
    asignado_al_deposito INT NULL,
    estado               VARCHAR(30),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (asignado_al_deposito) REFERENCES deposito_de_articulos (id)
);



CREATE TABLE conductor_de_vehiculo
(
    id_empresa          INT,
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    id_nt               INT,
    id_viejo            VARCHAR(10),
    nombre              VARCHAR(40),
    apellido            VARCHAR(30),
    domicilio           VARCHAR(100),
    numero_de_documento VARCHAR(20),
    id_vehiculo         INT,
    estado              VARCHAR(30),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculos (id)
);



CREATE TABLE vendedor
(
    id_empresa               INT,
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                    INT,
    id_viejo                 INT,
    nombre                   VARCHAR(100),
    direccion                VARCHAR(100),
    telefono                 VARCHAR(20),
    comision                 DECIMAL(6, 2),
    id_provincia_que_atiende VARCHAR(4),
    id_visual_fox            INT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_provincia_que_atiende) REFERENCES afip_provincias (id)
);


CREATE TABLE zonas_de_venta
(
    id_empresa        INT,
    id                INT AUTO_INCREMENT PRIMARY KEY,
    id_nt             INT,
    nombre_de_la_zona VARCHAR(100),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);


CREATE TABLE clientes
(
    id_empresa              INT,
    id                      INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                   INT,
    id_viejo                INT,
    es_extranjero           BIT,
    razon_social            VARCHAR(100),
    nombre_de_fantasia      VARCHAR(100),
    id_categoria_tributaria int,
    numero_ingresos_brutos  VARCHAR(20),
    tasa_de_ingresos_brutos DECIMAL(6, 2),
    id_iibb_regimen         VARCHAR(4) NULL,
    id_iibb_jurisdiccion    VARCHAR(4) NULL,
    inscripto_en_iibb       BIT,
    datos_controlados       BIT,
    factura_por_terceros    BIT,
    nombre_del_contacto     VARCHAR(40),
    mail                    VARCHAR(100),
    observaciones           VARCHAR(256) ,
	forma_de_pago					INT,
	lista_de_precio					SMALLINT DEFAULT 1,
	tipo_de_documento				VARCHAR(20) NOT NULL,
	numero_de_documento				VARCHAR(20) NOT NULL,
	direccion						VARCHAR(100),
	localidad						VARCHAR(100),
	id_provincia					VARCHAR(4) ,
	id_pais							VARCHAR(4),
	codigo_postal					VARCHAR(20) NULL,
	id_zonas_de_venta				INT,
	id_zona_de_rendicion			INT,
	direccion_de_envio				VARCHAR(100) NULL,
	localidad_de_envio				VARCHAR(100) NULL,
	id_provincia_de_envio			VARCHAR(4) NULL,
	telefono1						VARCHAR(40),
	telefono2						VARCHAR(40),
	telefono3						VARCHAR(40),
	limite_de_compra				DECIMAL(14,2),
	limite_de_credito				DECIMAL(14,2),
	id_imputacion					INT NULL,
	id_vendedor						INT NULL,
	estado							VARCHAR(20),
	fecha_conciliacion_cta_cte		DATE,
	bonificacion_general			DECIMAL(6,2),
	FOREIGN KEY (id_empresa)				REFERENCES empresa(id),
	FOREIGN KEY (id_nt)						REFERENCES transacciones(id),
	FOREIGN	KEY (id_provincia)				REFERENCES afip_provincias(id),
	FOREIGN	KEY (id_provincia_de_envio)		REFERENCES afip_provincias(id),
	FOREIGN KEY (id_pais)					REFERENCES afip_paises(id),
	FOREIGN KEY (id_imputacion)				REFERENCES plan_de_cuentas(id),
	FOREIGN KEY (id_vendedor)				REFERENCES vendedor(id),
	FOREIGN KEY (id_zonas_de_venta)			REFERENCES zonas_de_venta(id),
	FOREIGN KEY (id_zona_de_rendicion)		REFERENCES zona_de_rendicion(id),
	FOREIGN KEY (id_categoria_tributaria)	REFERENCES afip_categoria_tributaria(id)
);



CREATE TABLE sucursales_de_clientes
(
    id_empresa             INT,
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                  INT,
    id_cliente             INT,
    nombre_de_la_sucursal  VARCHAR(100),
    direccion              VARCHAR(100),
    coordenadas_google_map VARCHAR(100),
    tiempo_a_sucursal      TIME(0),
    distancia_a_sucursal   DECIMAL(10, 2),
    codigo_viejo           VARCHAR(30),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);

CREATE TABLE articulos_de_compra_y_ventas
(
    id_empresa                 INT,
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                      INT,
    id_viejo                   VARCHAR(15),
    producto_discontinuado     BIT,
    id_proveedor               INT NULL,
    codigo_de_barra            BIGINT,
    codigo_de_barra_verificado BIT,
    id_rubros_de_articulos     INT,
    grupo1                     VARCHAR(20),
    grupo2                     VARCHAR(20),
    detalle                    VARCHAR(200),
    marca                      VARCHAR(20),
    unidad                     VARCHAR(20),
    id_imputacion              INT,
    costo_actual               DECIMAL(14, 2),
    costo_nuevo                DECIMAL(14, 2),
    rentabilidad_1             DECIMAL(6, 2),
    lista_de_precio_1          DECIMAL(14, 2),
    rentabilidad_2             DECIMAL(6, 2),
    lista_de_precio_2          DECIMAL(14, 2),
    rentabilidad_3             DECIMAL(6, 2),
    lista_de_precio_3          DECIMAL(14, 2),
    rentabilidad_4             DECIMAL(6, 2),
    lista_de_precio_4          DECIMAL(14, 2),
    rentabilidad_5             DECIMAL(6, 2),
    lista_de_precio_5          DECIMAL(14, 2),
    rentabilidad_6             DECIMAL(6, 2),
    lista_de_precio_6          DECIMAL(14, 2),
    rentabilidad_7             DECIMAL(6, 2),
    lista_de_precio_7          DECIMAL(14, 2),
    rentabilidad_8             DECIMAL(6, 2),
    lista_de_precio_8          DECIMAL(14, 2),
    rentabilidad_9             DECIMAL(6, 2),
    lista_de_precio_9          DECIMAL(14, 2),
    tasa_iva                   DECIMAL(6, 2),
    id_moneda                  VARCHAR(4),
    peso_neto                  DECIMAL(14, 2),
    peso_bruto                 DECIMAL(14, 2),
    tiene_seguimiento_de_stock BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (ID_Rubros_de_articulos) REFERENCES rubros_de_articulos (id),
    FOREIGN KEY (id_imputacion) REFERENCES plan_de_cuentas (id),
    FOREIGN KEY (id_moneda) REFERENCES afip_monedas (id)
);


CREATE TABLE lista_de_precios_historial
(
    id_empresa                      INT,
    id_nt                           INT,
    id_articulos_de_compra_y_ventas INT,
    costo_viejo                     DECIMAL(14, 2),
    costo_nuevo                     DECIMAL(14, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_articulos_de_compra_y_ventas) REFERENCES articulos_de_compra_y_ventas (id)
);


CREATE TABLE bascula_movimientos
(
    id_empresa            INT,
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                 INT,
    fecha_creacion        DATETIME,
    fecha                 DATE,
    planta                INT,
    tipo_doc              INT,
    nro_remito_fiscal     INT,
    nro_remito_interno    INT,
    material_propiedad    VARCHAR(10),
    proveedor_origen      INT,
    planta_origen         INT,
    cliente_destino       INT,
    flete_propiedad       VARCHAR(10),
    flete_camion          VARCHAR(10),
    flete_chofer          VARCHAR(10),
    flete_hora_in         VARCHAR(5),
    flete_hora_out        VARCHAR(5),
    flete_lectura_tablero INT,
    bruto                 INT,
    tara                  INT,
    neto                  INT,
    bruto_prov            INT,
    tara_prov             INT,
    neto_prov             INT,
    codarticulo           VARCHAR(15),
    deposito              INT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE mis_comprobanes_de_compra
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    fecha      DATE,
    tipo       VARCHAR(40),
    pv         INT,
    numero     INT,
    numero_h   INT,
    cae        INT,
    tipo_doc   VARCHAR(10),
    nro_doc    VARCHAR(18),
    razon      VARCHAR(80),
    cambio     DECIMAL(14, 4),
    moneda     VARCHAR(10),
    neto_grav  DECIMAL(14, 2),
    neto_ngrav DECIMAL(14, 2),
    exento     DECIMAL(14, 2),
    iva        DECIMAL(14, 2),
    total      DECIMAL(14, 2),
    id_nt      INT,
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);



CREATE TABLE letra_de_comprobante
(
    id VARCHAR(1) PRIMARY KEY
);
INSERT INTO letra_de_comprobante (id)
VALUES ('A'),('B'),('C'),('M'),('X'),('E'),('R');


CREATE TABLE tipos_de_comprobante
(
    codigo      VARCHAR(2) PRIMARY KEY,
    descripcion VARCHAR(30)
);

INSERT INTO tipos_de_comprobante (descripcion, codigo)
VALUES ('Factura', 'FC'),
       ('Nota de Crédito', 'NC'),
       ('Nota de Débito', 'ND'),
       ('Recibo', 'RC'),
       ('Remito', 'RM'),
       ('Ajuste Manual - Crédito', 'CM'),
       ('Ajuste Manual - Dédito', 'DM'),
       ('Orden de Pago', 'OP');


CREATE TABLE tipos_de_movimientos
(
    codigo      VARCHAR(1) PRIMARY KEY,
    descripcion VARCHAR(30)
);

INSERT INTO tipos_de_movimientos (descripcion, codigo)
VALUES ('Entradas', 'E'),
       ('Salidas', 'S'),
       ('Transferencias', 'T');

CREATE TABLE proveedores
(
    id_empresa                            INT,
    id                                    INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                                 INT,
    id_viejo                              INT,
    razon_social                          VARCHAR(100),
    nombre_de_fantasia                    VARCHAR(100),
    agrupacion                            VARCHAR(15),
    proveedor_de_mercaderia               BIT,
    contacto                              VARCHAR(80),
    mail                                  VARCHAR(100),
    direccion                             VARCHAR(100),
    codigo_postal                         VARCHAR(20),
    localidad                             VARCHAR(40),
    id_provincia                          VARCHAR(4) NULL,
    telefono                              VARCHAR(100),
    id_categoria_tributaria               INT,
    id_imputacion_gastos                  INT NULL,
    id_imputacion_pasivo                  INT NULL,
    id_afip_tipos_de_documentos           VARCHAR(2),
    numero_de_documento                   VARCHAR(20) NOT NULL,
    por_cuenta_y_orden_de                 BIT,
    inscripto_en_ganancias                BIT,
    inscripto_en_iva                      BIT,
    inscripto_en_ingresos_brutos          BIT,
    inscripto_en_SUSS                     BIT,
    categoria_en_ganancias                DECIMAL(7, 2),
    categoria_en_iva                      INT,
    seguimiento_de_presupuestos           BIT,
    numero_de_inscripcion_iibb            VARCHAR(30),
    porcentaje_ingresos_brutos            DECIMAL(7, 2),
    tasa_de_ingresos_brutos               DECIMAL(7, 2),
    categoria_en_ingresos_brutos_san_luis VARCHAR(7),
    dias_de_vencimiento_de_factura        INT,
    porcentaje_percepcion_ingresos_brutos DECIMAL(7, 2),
    porcentaje_percepcion_iva             DECIMAL(7, 2),
    facturas_de_tercero                   BIT,
    tipo_de_proveedor                     VARCHAR(10),
    numero_de_cbu                         VARCHAR(30),
    alias_de_cbu                          VARCHAR(40),
    id_iibb_regimen                       VARCHAR(4) NULL,
    id_iibb_jurisdiccion                  VARCHAR(4) NULL,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_provincia) REFERENCES afip_provincias (id),
    FOREIGN KEY (id_imputacion_gastos) REFERENCES plan_de_cuentas (id),
    FOREIGN KEY (id_imputacion_pasivo) REFERENCES plan_de_cuentas (id),
    FOREIGN KEY (id_afip_tipos_de_documentos) REFERENCES afip_tipos_de_documentos (codigo),
    FOREIGN KEY (id_categoria_tributaria) REFERENCES afip_categoria_tributaria (id)
);



CREATE TABLE presupuestos_de_venta
(
    id_empresa                INT,
    id                        INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                     INT,
    tipo_de_remito            SMALLINT DEFAULT 1,
    fecha_del_presupuesto     DATE,
    fecha_de_vencimiento      DATE,
    fecha_de_entrega          DATE,
    id_clientes               INT NULL,
    id_sucursales_de_clientes INT NULL,
    monto_anticipado          DECIMAL(14, 2),
    neto                      DECIMAL(14, 2),
    percepciones              DECIMAL(14, 2),
    iva                       DECIMAL(14, 2),
    total                     DECIMAL(14, 2),
    presupuesto_aprobado      BIT,
    fecha_de_confirmacion     DATE,
    fecha_de_cierre           DATE,
    numero_de_orden_de_compra VARCHAR(30),
    estado                    SMALLINT,
    observaciones             VARCHAR(256) ,
	contacto_para_la_entrega				VARCHAR(30),
	tipo_de_estructura						VARCHAR(30),
	lleva_encuenta							BIT DEFAULT 0,
	caracteristicas_especiales				VARCHAR(30),
	FOREIGN KEY (id_empresa)				REFERENCES empresa(id),
	FOREIGN KEY (id_nt)						REFERENCES transacciones(id),
	FOREIGN KEY (id_clientes)				REFERENCES clientes(id),
	FOREIGN KEY (id_sucursales_de_clientes)	REFERENCES sucursales_de_clientes(id)
);


CREATE TABLE presupuestos_de_venta_detalle
(
    id_empresa                      INT,
    id                              INT AUTO_INCREMENT PRIMARY KEY,
    id_presupuestos_de_venta        INT,
    orden_de_los_items              SMALLINT,
    id_articulos_de_compra_y_ventas INT,
    descripcion                     VARCHAR(100),
    detalle                         VARCHAR(256) ,
	cantidad								DECIMAL(14,2),
	lleva_flete								SMALLINT,
	precio_unitario							DECIMAL(14,2),
	tasa_iva								DECIMAL(14,2),
	total									DECIMAL(14,2),
	FOREIGN KEY (id_empresa)							REFERENCES empresa(id),
	FOREIGN KEY (id_presupuestos_de_venta)				REFERENCES presupuestos_de_venta(id),
	FOREIGN KEY (id_articulos_de_compra_y_ventas)		REFERENCES articulos_de_compra_y_ventas(id)
);

CREATE TABLE documentos_de_venta_cabecera
(
    id_empresa                      INT,
    id                              INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                           INT,
    id_clientes                     INT NULL,
    id_sucursales_de_clientes       INT NULL,

    nombre_cliente_consumidor_final VARCHAR(100)   DEFAULT ' ',

    punto_de_venta_interno          DECIMAL(6),
    numero_del_documento_interno    DECIMAL(10),

    punto_de_venta_fiscal           DECIMAL(6),
    numero_del_documento_fiscal     DECIMAL(10),

    id_tipos_de_comprobante         VARCHAR(2),
    id_letra_de_comprobante         VARCHAR(1),
    id_vendedor                     INT,

    fecha                           DATE,
    debe                            decimal(14, 2) DEFAULT 0,
    haber                           decimal(14, 2) DEFAULT 0,
    importe_cancelado               decimal(14, 2) DEFAULT 0,
    saldo_pendiente                 decimal(14, 2) DEFAULT 0,
    anticipo                        decimal(14, 2) DEFAULT 0,
    id_proveedores                  INT NULL,
    forma_de_pago                   VARCHAR(10),
    fecha_de_vencimiento            DATE,

    observaciones                   VARCHAR(300)   DEFAULT ' ',
    numeros_de_remitos_vinculados   VARCHAR(256) DEFAULT ' ',
	id_presupuesto							INT NULL,
	id_moneda								VARCHAR(4)  DEFAULT 'PES',
	cambio									DECIMAL(14,4) DEFAULT 1,
	comision_total_importe					DECIMAL(14,4) DEFAULT 0,
	comision_total_porcentaje				DECIMAL(8,3) DEFAULT 0,

	es_factura_por_anticipo_de_pago			BIT DEFAULT 0,
	es_factura_por_canje					BIT DEFAULT 0,
	factura_anticipada_canje_finalizada		BIT DEFAULT 0,

	factura_vinculada_id					INT,
	factura_vinculada_punto_de_venta		DECIMAL(6) DEFAULT 0,
	factura_vinculada_numero				DECIMAL(10) DEFAULT 0,

	el_remito_fue_facturado					BIT DEFAULT 0,

	factura_de_producto_o_servicio			DECIMAL(1) DEFAULT 0,
	id_zona_de_rendicion					INT NULL,
	fecha_de_rendicion						DATE,
	fecha_de_presentacion					DATE,
	mueve_stock								BIT DEFAULT 1,
	valor_declarado							DECIMAL(14,2) DEFAULT 0,
	peso_por_caja							DECIMAL(14,2) DEFAULT 0,
	FOREIGN KEY (id_empresa)				REFERENCES empresa(id),
	FOREIGN KEY (id_nt)						REFERENCES transacciones(id),
	FOREIGN KEY (id_clientes)				REFERENCES clientes(id),
	FOREIGN KEY (id_sucursales_de_clientes)	REFERENCES sucursales_de_clientes(id),
	FOREIGN KEY (id_tipos_de_comprobante)	REFERENCES tipos_de_comprobante(codigo),
	FOREIGN KEY (id_letra_de_comprobante)	REFERENCES letra_de_comprobante(id),
	FOREIGN KEY (id_vendedor)				REFERENCES vendedor(id),
	FOREIGN KEY (id_proveedores)			REFERENCES proveedores(id),
	FOREIGN KEY (id_moneda)					REFERENCES afip_monedas(id),
    FOREIGN KEY (id_zona_de_rendicion)		REFERENCES zona_de_rendicion(id),
	FOREIGN KEY (factura_vinculada_id)		REFERENCES documentos_de_venta_cabecera(id) ON DELETE NO ACTION
);


CREATE TABLE documento_de_venta_detalle_de_envios
(
    id_empresa                          INT,
    id                                  INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                               INT,
    id_documentos_de_venta_cabecera     INT,
    numero_de_traslado                  VARCHAR(30),
    planta                              INT,
    silo                                INT,
    id_camion                           INT NULL,
    id_bomba                            INT NULL,
    operacion                           SMALLINT,
    id_chofer                           INT NULL,
    remito_orden                        SMALLINT,
    hora_de_salida_de_planta            TIME,
    hora_de_ingreso_a_planta            TIME,
    incluye_flete                       BIT,
    hora_de_inicio_de_carga             TIME,
    hora_de_fin_de_carga                TIME,
    hora_de_llegada_a_obra              TIME,
    hora_de_inicio_de_descarga          TIME,
    horario_comprometido_del_turno      TIME,
    tiempo_total_en_obra                TIME,
    tiempo_para_llegar_a_obra           TIME,
    distancia_a_obra                    DECIMAL(10, 2),
    hora_de_salida_de_planta_programada TIME,
    hora_de_ingreso_a_planta_programada TIME,
    hora_de_inicio_de_carga_programada  TIME,
    hora_de_fin_de_carga_programada     TIME,
    turno_confirmado                    BIT,
    numero_de_encuesta                  INT,
    encuesta_pregunta1                  SMALLINT,
    encuesta_pregunta2                  SMALLINT,
    encuesta_pregunta3                  SMALLINT,
    encuesta_pregunta4                  SMALLINT,
    encuesta_pregunta5                  SMALLINT,
    fue_impreso                         BIT,
    numero_de_precinto                  INT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_documentos_de_venta_cabecera) REFERENCES documentos_de_venta_cabecera (id),
    FOREIGN KEY (id_camion) REFERENCES vehiculos (id),
    FOREIGN KEY (id_bomba) REFERENCES vehiculos (id),
    FOREIGN KEY (id_chofer) REFERENCES conductor_de_vehiculo (id)
);



CREATE TABLE documentos_de_venta_detalle
(
    id_empresa                       INT,
    id                               INT AUTO_INCREMENT PRIMARY KEY,
    id_documentos_de_venta_cabecera  INT,
    id_articulos_de_compra_y_ventas  INT,
    detalle                          VARCHAR(600),
    cantidad_que_ingresa             DECIMAL(14, 2),
    cantidad_que_sale                DECIMAL(14, 2),
    id_deposito_de_articulos         INT,
    precio_unitario_de_venta         DECIMAL(14, 2),
    precio_unitario_original         DECIMAL(14, 2),
    costo                            DECIMAL(14, 2),
    bonificacion                     DECIMAL(14, 2),
    comision                         DECIMAL(14, 2),
    agua                             INT,
    registro_de_beton_plus           INT,
    numero_de_tramite_inv            VARCHAR(30),
    numero_de_trazabilidad_etiquetas VARCHAR(30),
    numero_de_traslado               VARCHAR(20),
    FOREIGN KEY (id_documentos_de_venta_cabecera) REFERENCES documentos_de_venta_cabecera (id),
    FOREIGN KEY (id_articulos_de_compra_y_ventas) REFERENCES articulos_de_compra_y_ventas (id),
    FOREIGN KEY (id_deposito_de_articulos) REFERENCES deposito_de_articulos (id)
);



CREATE TABLE documentos_de_venta_libro_iva_ventas
(
    id_empresa                             INT,
    id                                     INT AUTO_INCREMENT PRIMARY KEY,
    id_documentos_de_venta_cabecera        INT,
    esta_pendiete_de_aprobacion_afip       BIT,
    estado_del_tramite_del_cae             VARCHAR(30),
    CAE                                    DECIMAL(20),
    fecha_vencimiento_cae                  DATE,
    id_afip_tipos_de_documentos            VARCHAR(2),
    id_afip_tipos_de_comprobantes_de_venta VARCHAR(3),
    neto_1                                 DECIMAL(14, 2),
    neto_2                                 DECIMAL(14, 2),
    neto_3                                 DECIMAL(14, 2),
    iva_1                                  DECIMAL(14, 2),
    iva_2                                  DECIMAL(14, 2),
    iva_3                                  DECIMAL(14, 2),
    importe_no_gravado                     DECIMAL(14, 2),
    operacion_exenta                       DECIMAL(14, 2),
    retenciones                            DECIMAL(14, 2),
    percepcion_iva                         DECIMAL(14, 2),
    percepcion_iibb                        DECIMAL(14, 2),
    total                                  DECIMAL(14, 2),
    alicuota_iva_1                         DECIMAL(7, 2),
    alicuota_iva_2                         DECIMAL(7, 2),
    alicuota_iva_3                         DECIMAL(7, 2),
    alivanoins                             DECIMAL(7, 2),
    alicuota_retenciones                   DECIMAL(7, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_documentos_de_venta_cabecera) REFERENCES documentos_de_venta_cabecera (id),
    FOREIGN KEY (id_afip_tipos_de_documentos) REFERENCES afip_tipos_de_documentos (codigo),
    FOREIGN KEY (id_afip_tipos_de_comprobantes_de_venta) REFERENCES afip_tipos_de_comprobantes_de_venta (codigo)
);



CREATE TABLE document5os_de_venta_detalle_de_retenciones_sufridas
(
    id                              INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa                      INT,
    id_documentos_de_venta_cabecera INT,
    id_afip_tipos_de_retenciones    VARCHAR(30),
    numero_de_retencion             DECIMAL(12),
    fecha_de_la_retencion           DATE,
    importe_neto_afectado           DECIMAL(14, 2),
    importe_retenido                DECIMAL(14, 2),
    FOREIGN KEY (id_documentos_de_venta_cabecera) REFERENCES documentos_de_venta_cabecera (id),
    FOREIGN KEY (id_afip_tipos_de_retenciones) REFERENCES afip_tipos_de_retenciones (id)
);



CREATE TABLE documentos_de_compra_cabecera
(
    id_empresa               INT,
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                    INT,
    id_proveedores           INT NULL,
    fecha                    DATE,
    id_tipos_de_comprobante  VARCHAR(2),
    id_letra_de_comprobante  VARCHAR(1),
    punto_de_venta           DECIMAL(6),
    numero_del_documento     DECIMAL(10),
    debe                     decimal(14, 2) DEFAULT 0,
    haber                    decimal(14, 2) DEFAULT 0,
    importe_cancelado        decimal(14, 2) DEFAULT 0,
    saldo_pendiente          decimal(14, 2) DEFAULT 0,
    anticipo                 decimal(14, 2) DEFAULT 0,
    fecha_de_vencimiento     DATE,
    observaciones            VARCHAR(300)   DEFAULT ' ',
    id_moneda                VARCHAR(4)     DEFAULT 'PES',
    cambio                   DECIMAL(14, 4) DEFAULT 1,
    mueve_stock              BIT            DEFAULT 1,
    orden_de_pago_finalizada BIT            DEFAULT 0,
    tipo_de_orden_de_pago    SMALLINT,
    la_op_esta_en_edicion    BIT            DEFAULT 0,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_proveedores) REFERENCES proveedores (id),
    FOREIGN KEY (id_tipos_de_comprobante) REFERENCES tipos_de_comprobante (codigo),
    FOREIGN KEY (id_letra_de_comprobante) REFERENCES letra_de_comprobante (id)
);



CREATE TABLE entidades_financieras
(
    id_empresa                              INT,
    id                                      INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                                   INT,

    numero_de_cuenta                        VARCHAR(15),
    nombre                                  VARCHAR(50),
    cuit                                    VARCHAR(20),
    imputacion_ch_diferidos                 INT,
    imputacion_a_banco                      INT,
    imputacion_transferencias_a_identificar INT NULL,
    tipo_de_entidad_financiera              VARCHAR(10),
    cbu                                     VARCHAR(30),
    alias                                   VARCHAR(50),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (imputacion_ch_diferidos) REFERENCES plan_de_cuentas (id),
    FOREIGN KEY (imputacion_a_banco) REFERENCES plan_de_cuentas (id),
    FOREIGN KEY (imputacion_transferencias_a_identificar) REFERENCES plan_de_cuentas (id)
);


CREATE TABLE chequeras
(
    id_empresa               INT,
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                    INT,
    id_viejo                 INT,
    id_entidades_financieras INT,
    esta_activa              BIT,
    numero_de_serie          VARCHAR(10),
    numero_desde             INT,
    numero_hasta             INT,
    formato_del_cheque       VARCHAR(100),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_entidades_financieras) REFERENCES entidades_financieras (id)
);


CREATE TABLE conciliacion
(
    id_empresa               INT,
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                    INT,

    id_entidades_financieras INT,
    fecha                    DATE NOT NULL,
    saldo_anterior           DECIMAL(14, 2),
    saldo_actual             DECIMAL(14, 2),
    debe                     DECIMAL(14, 2),
    haber                    DECIMAL(14, 2),
    fecha_de_cierre          DATE,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_entidades_financieras) REFERENCES entidades_financieras (id)
);

CREATE TABLE cuenta_corriente_Entidades_financieras
(
    id_empresa                 INT,
    id                         INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                      INT,

    id_entidades_financieras   INT,
    tipo_de_movimiento         VARCHAR(20) NOT NULL,
    nro_identif_del_movimiento INT         NOT NULL,
    fecha                      DATE        NOT NULL,
    fecha_de_vencimiento       DATE,
    debe                       DECIMAL(14, 2),
    haber                      DECIMAL(14, 2),
    detalle                    VARCHAR(100),
    id_concilia                INT NULL,
    emitido_a                  VARCHAR(100),
    cheque_anulado             BIT,
    importe_anulado_del_ch     DECIMAL(14, 2),
    es_cheque_electronico      BIT,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_entidades_financieras) REFERENCES entidades_financieras (id),
    FOREIGN KEY (id_concilia) REFERENCES conciliacion (id)
);

CREATE TABLE cheques_de_terceros
(
    id_empresa                          INT,
    id                                  INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                               INT,
    id_cliente                          INT,
    cuit_del_emisor                     VARCHAR(20) NOT NULL,
    sucursal                            VARCHAR(100),
    tipo_de_comprobante                 VARCHAR(2),
    letra_del_comprobante               VARCHAR(1),
    fecha                               DATE,
    fecha_del_cheque                    DATE,
    numero_del_cheque                   INT,
    nombre_del_banco                    VARCHAR(40),
    importe                             DECIMAL(14, 2),
    recibido_de                         VARCHAR(100),
    es_cheque_electronico               BIT,
    entregado_a                         VARCHAR(100),
    estado                              VARCHAR(10) CHECK (estado IN ('EN CARTERA', 'DEPOSITADO', 'ENTREGADO', 'RECHAZADO')),
    fecha_de_salida                     DATE,
    numero_del_documento                INT,
    id_nt_Documentos_de_compra_cabecera INT NULL,

    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_cliente) REFERENCES clientes (id),
    FOREIGN KEY (id_nt_Documentos_de_compra_cabecera) REFERENCES documentos_de_compra_cabecera (id)
);

CREATE TABLE documentos
(
    id_empresa             INT,
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                  INT,
    id_cuenta_corriente    INT,
    tipo_de_comprobante    VARCHAR(2),
    letra_del_comprobante  VARCHAR(1),
    numero_del_comprobante INT,
    debe                   DECIMAL(14, 2),
    haber                  DECIMAL(14, 2),
    recibido_de            VARCHAR(40),
    entregado_a            VARCHAR(40),
    fecha_de_recibido      DATE,
    fecha_de_vencimiento   DATE,
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);

CREATE TABLE efectivo
(
    id_empresa             INT,
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    id_nt                  INT,
    id_cuenta_corriente    INT,
    fecha                  DATE,
    tipo_de_comprobante    VARCHAR(2),
    letra_del_comprobante  VARCHAR(1),
    numero_del_comprobante INT,
    detalle                VARCHAR(100),
    debe                   DECIMAL(14, 2),
    haber                  DECIMAL(14, 2),
    id_moneda              VARCHAR(4),
    cambio                 DECIMAL(14, 4),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id),
    FOREIGN KEY (id_moneda) REFERENCES afip_monedas (id)
);

CREATE TABLE arrastre_de_caja
(
    id_empresa                   INT,
    id                           INT AUTO_INCREMENT PRIMARY KEY,
    fecha                        DATE,
    ajuste                       DECIMAL(14, 2),
    fecha_anterior               DATE,
    saldo_anterior               DECIMAL(14, 2),
    ingresos                     DECIMAL(14, 2),
    egresos                      DECIMAL(14, 2),
    saldo_actual                 DECIMAL(14, 2),
    saldo_en_efectivo            DECIMAL(14, 2),
    saldo_en_cheques_de_terceros DECIMAL(14, 2),
    saldo_en_tarjetas            DECIMAL(14, 2),
    saldo_en_cuenta_corriente    DECIMAL(14, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id)
);

CREATE TABLE cierre_de_caja_diaria
(
    id_empresa       INT,
    id               INT AUTO_INCREMENT PRIMARY KEY,
    id_nt            INT,
    fecha_de_cierre  DATE,
    id_cajero        INT,
    total_del_cierre DECIMAL(14, 2),
    FOREIGN KEY (id_empresa) REFERENCES empresa (id),
    FOREIGN KEY (id_nt) REFERENCES transacciones (id)
);