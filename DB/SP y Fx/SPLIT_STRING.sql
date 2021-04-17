create definer = root@localhost function SPLIT_STRING(str varchar(255), delim varchar(12), pos int) returns varchar(255) no sql
RETURN REPLACE(	
    SUBSTRING(
		SUBSTRING_INDEX(str , delim , pos) ,
		CHAR_LENGTH(
			SUBSTRING_INDEX(str , delim , pos - 1)
		) + 1
	) ,
	delim ,
	''
);

