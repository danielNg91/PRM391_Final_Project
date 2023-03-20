DECLARE @cnt INT = 1;

WHILE @cnt <= 30
BEGIN
DECLARE @price INT = (@cnt * 100);
 insert products values(CONCAT('T-Shirt',@cnt),CONCAT(@price,''),10,'12-03-2022',null,'description',1,'false');
 SET @cnt = @cnt + 1;
END;
GO

DECLARE @cnt INT = 1;

WHILE @cnt <= 30
BEGIN
DECLARE @price INT = (@cnt * 100);
 insert products values(CONCAT('Jean',@cnt),CONCAT(@price,''),10,'12-03-2022',null,'description',2,'false');
 SET @cnt = @cnt + 1;
END;
GO