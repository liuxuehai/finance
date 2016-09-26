BEGIN
	#Routine body goes here...
   declare var1 varchar(50);
   DECLARE l_sql VARCHAR(4000);
   DECLARE t_sql VARCHAR(4000);
   DECLARE tableName VARCHAR(80);
   declare no_more_departments integer DEFAULT 0;     

   DECLARE C_RESULT CURSOR FOR
             SELECT code  FROM StockInfo;
   DECLARE CONTINUE HANDLER FOR NOT FOUND
             SET no_more_departments=1;
   
   SET l_sql='SELECT code  FROM StockInfo;';
   SET @sql=l_sql;
   PREPARE s1 FROM @sql;
   EXECUTE s1;
   deallocate prepare s1;


  OPEN C_RESULT;   
     REPEAT
       FETCH C_RESULT INTO var1; 


       SET tableName=CONCAT('Price',var1);
       SET tableName=CONCAT('Price',var1);

       SET t_sql=CONCAT('CREATE TABLE ',tableName,'  like Price900957_copy ;');
       SET @sql=t_sql;
       PREPARE s2 FROM @sql;
       EXECUTE s2;
       deallocate prepare s2;

       UNTIL no_more_departments  END REPEAT;  

   CLOSE C_RESULT; 

   
END


