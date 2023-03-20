
DECLARE @cnt INT = 1;

WHILE @cnt <= 60
BEGIN
 insert into images values('https://cdn-images.italist.com/image/upload/t_zoom_v3_q_auto/209c197b638ccd99d013e10997e56f39.jpg',@cnt)
 insert into images values('https://cdn-images.italist.com/image/upload/t_zoom_v3_q_auto/209c197b638ccd99d013e10997e56f39.jpg',@cnt)
 insert into images values('https://cdn-images.italist.com/image/upload/t_zoom_v3_q_auto/209c197b638ccd99d013e10997e56f39.jpg',@cnt)
 SET @cnt = @cnt + 1;
END;
GO