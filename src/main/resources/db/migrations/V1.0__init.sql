CREATE TABLE ZIP_CODE (
	id serial NOT NULL ,
	name varchar(150) NOT NULL,
	CONSTRAINT zip_code_name_key UNIQUE (name),
	CONSTRAINT zip_code_pkey PRIMARY KEY (id)
);
COMMENT ON COLUMN ZIP_CODE.name IS '名稱';