USE [master]
GO

/****** Object:  Database [IntuitionDatabase]    Script Date: 1/31/2022 11:55:10 AM ******/
CREATE DATABASE [IntuitionDatabase]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'IntuitionDatabase', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\IntuitionDatabase.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'IntuitionDatabase_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\IntuitionDatabase_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

ALTER DATABASE [IntuitionDatabase] SET COMPATIBILITY_LEVEL = 120
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [IntuitionDatabase].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [IntuitionDatabase] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET ARITHABORT OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [IntuitionDatabase] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [IntuitionDatabase] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET  DISABLE_BROKER 
GO

ALTER DATABASE [IntuitionDatabase] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [IntuitionDatabase] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [IntuitionDatabase] SET  MULTI_USER 
GO

ALTER DATABASE [IntuitionDatabase] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [IntuitionDatabase] SET DB_CHAINING OFF 
GO

ALTER DATABASE [IntuitionDatabase] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [IntuitionDatabase] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO

ALTER DATABASE [IntuitionDatabase] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [IntuitionDatabase] SET  READ_WRITE 
GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[addresses]    Script Date: 1/31/2022 11:55:28 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[addresses](
	[id] [bigint] NOT NULL,
	[value] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_addresses] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[categories]    Script Date: 1/31/2022 11:55:39 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[categories](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[created_date] [nvarchar](50) NOT NULL,
	[last_modified] [nvarchar](50) NULL,
	[is_deleted] [bit] NOT NULL,
 CONSTRAINT [PK_categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[images]    Script Date: 1/31/2022 11:56:08 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[images](
	[id] [bigint] NOT NULL,
	[link] [nvarchar](500) NOT NULL,
	[product_id] [bigint] NOT NULL,
 CONSTRAINT [PK_images] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[images]  WITH CHECK ADD  CONSTRAINT [FK_images_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO

ALTER TABLE [dbo].[images] CHECK CONSTRAINT [FK_images_products]
GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[order_details]    Script Date: 1/31/2022 11:56:13 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[order_details](
	[id] [bigint] NOT NULL,
	[product_id] [bigint] NOT NULL,
	[quantity] [int] NOT NULL,
	[order_detail_price] [nvarchar](50) NOT NULL,
	[order_id] [bigint] NOT NULL,
 CONSTRAINT [PK_order_details] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_orders] FOREIGN KEY([order_id])
REFERENCES [dbo].[orders] ([id])
GO

ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_orders]
GO

ALTER TABLE [dbo].[order_details]  WITH CHECK ADD  CONSTRAINT [FK_order_details_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO

ALTER TABLE [dbo].[order_details] CHECK CONSTRAINT [FK_order_details_products]
GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[orders]    Script Date: 1/31/2022 11:56:34 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[orders](
	[id] [bigint] NOT NULL,
	[created_date] [nvarchar](50) NOT NULL,
	[user_id] [bigint] NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[order_price] [nvarchar](50) NOT NULL,
	[promotion_id] [bigint] NULL,
 CONSTRAINT [PK_orders] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_promotions] FOREIGN KEY([promotion_id])
REFERENCES [dbo].[promotions] ([id])
GO

ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_promotions]
GO

ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK_orders_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO

ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK_orders_users]
GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[products]    Script Date: 1/31/2022 11:56:39 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[products](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[price] [nvarchar](30) NOT NULL,
	[quantity] [int] NOT NULL,
	[created_date] [nvarchar](50) NOT NULL,
	[last_modified] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[cate_id] [bigint] NOT NULL,
	[is_deleted] [bit] NOT NULL,
 CONSTRAINT [PK_products] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FK_products_categories] FOREIGN KEY([cate_id])
REFERENCES [dbo].[categories] ([id])
GO

ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FK_products_categories]
GO

USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[promotions]    Script Date: 1/31/2022 11:56:48 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[promotions](
	[id] [bigint] NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[discount_percent] [int] NOT NULL,
	[discount_amout] [int] NOT NULL,
	[created_date] [nvarchar](50) NOT NULL,
	[issue_date] [nvarchar](50) NOT NULL,
	[is_used] [bit] NOT NULL,
 CONSTRAINT [PK_promotions] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[users]    Script Date: 1/31/2022 11:56:55 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](200) NOT NULL,
	[fullname] [nvarchar](100) NOT NULL,
	[phone_number] [nvarchar](20) NOT NULL,
	[email] [nvarchar](200) NOT NULL,
	[created_date] [nvarchar](50) NOT NULL,
	[last_modified] [nvarchar](50) NULL,
	[avatar] [nvarchar](1000) NULL,
	[role] [nvarchar](50) NOT NULL,
	[rank] [nvarchar](10) NULL,
	[is_deleted] [bit] NOT NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO


USE [IntuitionDatabase]
GO

/****** Object:  Table [dbo].[users_addresses]    Script Date: 1/31/2022 11:57:01 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[users_addresses](
	[user_id] [bigint] NULL,
	[address_id] [bigint] NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[users_addresses]  WITH CHECK ADD  CONSTRAINT [FK_users_addresses_addresses] FOREIGN KEY([address_id])
REFERENCES [dbo].[addresses] ([id])
GO

ALTER TABLE [dbo].[users_addresses] CHECK CONSTRAINT [FK_users_addresses_addresses]
GO

ALTER TABLE [dbo].[users_addresses]  WITH CHECK ADD  CONSTRAINT [FK_users_addresses_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO

ALTER TABLE [dbo].[users_addresses] CHECK CONSTRAINT [FK_users_addresses_users]
GO






