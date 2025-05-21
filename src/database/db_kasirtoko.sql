-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2025 at 10:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasirtoko`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `cetakBilanganGanjil` (IN `batas` INT)   BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= batas DO
        IF MOD(i, 2) = 1 THEN
            INSERT INTO hasil_ganjil VALUES (i);
        END IF;
        SET i = i + 1;
    END WHILE;

    -- Menampilkan hasil
    SELECT * FROM stok_produk;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang_rusak`
--

CREATE TABLE `barang_rusak` (
  `id_rusak` int(11) NOT NULL,
  `id_produk` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `alasan` enum('Rusak','Kadaluarsa','Berubah Pikiran') NOT NULL,
  `tanggal_rusak` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang_rusak`
--

INSERT INTO `barang_rusak` (`id_rusak`, `id_produk`, `jumlah`, `alasan`, `tanggal_rusak`) VALUES
(1, 'P001', 1, 'Kadaluarsa', '2025-05-19 17:00:00'),
(2, 'p002', 5, 'Rusak', '2025-05-20 17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `id_detail` int(11) NOT NULL,
  `id_pembelian` int(11) NOT NULL,
  `id_produk` varchar(11) NOT NULL,
  `id_stok` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `harga_beli` decimal(10,2) NOT NULL,
  `tanggal_kedaluwarsa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_pembelian`
--

INSERT INTO `detail_pembelian` (`id_detail`, `id_pembelian`, `id_produk`, `id_stok`, `jumlah`, `subtotal`, `harga_beli`, `tanggal_kedaluwarsa`) VALUES
(1, 8, 'p001', 3, 1, 10000.00, 10000.00, '2025-05-23'),
(2, 9, 'p011', 4, 10, 50000.00, 5000.00, '2025-05-29'),
(3, 10, 'P013', 5, 2, 70000.00, 35000.00, '2025-05-07'),
(4, 11, 'p001', 6, 1, 5000.00, 5000.00, '2025-05-31'),
(5, 11, 'p001', 7, 1, 10000.00, 10000.00, '2025-05-26'),
(6, 12, 'p001', 8, 1, 1000.00, 1000.00, '2025-05-29'),
(7, 13, 'p001', 9, 1, 2000.00, 2000.00, '2025-05-30'),
(8, 14, 'p003', 10, 1, 5000.00, 5000.00, '2025-05-01'),
(9, 14, 'p003', 11, 1, 5000.00, 5000.00, '2025-05-17'),
(10, 15, 'p003', 12, 1, 10000.00, 10000.00, '2025-05-23'),
(11, 16, 'p001', 13, 1, 2000.00, 2000.00, '2025-05-31'),
(12, 16, 'p001', 14, 1, 1000.00, 1000.00, '2025-05-17'),
(13, 17, 'p003', 15, 1, 5000.00, 5000.00, '2025-05-29'),
(14, 17, 'p003', 16, 1, 1000.00, 1000.00, '2025-05-24'),
(15, 18, 'p003', 17, 1, 200.00, 200.00, '2025-05-31'),
(16, 18, 'p003', 18, 1, 100.00, 100.00, '2025-05-15'),
(17, 19, 'p003', 19, 1, 100.00, 100.00, '2025-05-16'),
(18, 20, 'p003', 20, 2, 400.00, 200.00, '2025-05-28'),
(19, 21, 'p003', 21, 1, 200.00, 200.00, '2025-05-31'),
(20, 21, 'p003', 22, 1, 100.00, 100.00, '2025-05-29'),
(21, 22, 'p003', 23, 1, 100.00, 100.00, '2025-05-17'),
(22, 22, 'p004', 24, 1, 100.00, 100.00, '2025-05-28'),
(23, 23, 'p003', 25, 1, 300.00, 300.00, '2025-05-22'),
(24, 24, 'p003', 26, 1, 200.00, 200.00, '2025-05-30'),
(25, 25, 'p003', 22, 1, 100.00, 100.00, '2025-05-29'),
(26, 26, 'p014', 27, 1, 100.00, 100.00, '2025-05-15'),
(27, 27, 'p003', 28, 11, 1100.00, 100.00, '2025-05-16'),
(28, 28, 'p003', 28, 20, 2000.00, 100.00, '2025-05-16'),
(29, 29, 'P001', 29, 1, 15000.00, 15000.00, '2025-05-29'),
(30, 30, 'p001', 30, 1, 15000.00, 15000.00, '2025-05-30'),
(31, 31, 'p002', 31, 10, 30000.00, 3000.00, '2025-05-28');

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_detail` int(11) NOT NULL,
  `id_penjualan` varchar(10) NOT NULL,
  `id_produk` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `harga_beli` decimal(10,2) DEFAULT NULL,
  `harga_jual` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_detail`, `id_penjualan`, `id_produk`, `jumlah`, `subtotal`, `harga_beli`, `harga_jual`) VALUES
(9, 'RTX04', 'P001', 1, 10000.00, 5000.00, 10000.00),
(10, '', 'P001', 1, 10000.00, 5000.00, 10000.00),
(11, '', 'P001', 2, 20000.00, 5000.00, 10000.00),
(12, '', 'P001', 4, 40000.00, 5000.00, 10000.00),
(13, 'RTX05', 'P001', 1, 10000.00, 5000.00, 10000.00),
(14, 'RTX05', 'P001', 3, 30000.00, 5000.00, 10000.00),
(15, 'RTX05', 'P001', 8, 80000.00, 5000.00, 10000.00),
(16, 'RTX06', 'P001', 1, 10000.00, 5000.00, 10000.00),
(17, 'RTX07', 'P001', 1, 10000.00, 5000.00, 10000.00),
(18, 'RTX08', 'P001', 1, 10000.00, 5000.00, 10000.00),
(19, 'RTX09', 'P001', 2, 20000.00, 5000.00, 10000.00),
(20, 'RTX10', 'P001', 1, 10000.00, 5000.00, 10000.00),
(21, 'RTX11', 'P001', 2, 20000.00, 5000.00, 10000.00),
(22, 'RTX12', 'P001', 2, 20000.00, 5000.00, 10000.00),
(23, 'RTX13', 'P001', 2, 20000.00, 5000.00, 10000.00),
(24, 'RTX14', 'P001', 1, 10000.00, 5000.00, 10000.00),
(25, 'RTX14', 'P001', 1, 10000.00, 5000.00, 10000.00),
(26, 'RTX14', 'P001', 1, 10000.00, 5000.00, 10000.00),
(27, 'RTX15', 'P001', 1, 10000.00, 5000.00, 10000.00),
(28, 'RTX15', 'P001', 3, 30000.00, 5000.00, 10000.00),
(32, 'RTX16', 'P001', 1, 10000.00, 5000.00, 10000.00),
(33, 'RTX16', 'P001', 1, 10000.00, 5000.00, 10000.00),
(34, 'RTX17', 'P001', 1, 10000.00, 5000.00, 10000.00),
(35, 'RTX18', 'P001', 1, 10000.00, 5000.00, 10000.00),
(36, 'RTX18', 'P001', 1, 10000.00, 5000.00, 10000.00),
(37, 'RTX18', 'P001', 1, 10000.00, 5000.00, 10000.00),
(38, 'RTX19', 'P001', 1, 10000.00, 5000.00, 10000.00),
(39, 'RTX20', 'P001', 1, 10000.00, 5000.00, 10000.00),
(40, 'RTX21', 'P001', 2, 20000.00, 5000.00, 10000.00),
(41, 'RTX22', 'P001', 1, 10000.00, 5000.00, 10000.00),
(42, 'RTX22', 'P001', 1, 10000.00, 5000.00, 10000.00),
(43, 'RTX23', 'P001', 1, 10000.00, 5000.00, 10000.00),
(44, 'RTX24', 'P001', 1, 10000.00, 5000.00, 10000.00),
(45, 'RTX25', 'P001', 1, 10000.00, 5000.00, 10000.00),
(46, 'RTX25', 'P001', 1, 10000.00, 5000.00, 10000.00),
(47, 'RTX26', 'P001', 1, 10000.00, 5000.00, 10000.00),
(48, 'RTX27', 'P001', 1, 10000.00, 5000.00, 10000.00),
(49, 'RTX28', 'P001', 1, 10000.00, 5000.00, 10000.00),
(50, 'RTX29', 'P001', 1, 10000.00, 5000.00, 10000.00),
(51, 'RTX30', 'P001', 1, 10000.00, 5000.00, 10000.00),
(52, 'RTX31', 'P001', 1, 10000.00, 5000.00, 10000.00),
(53, 'RTX32', 'P001', 1, 10000.00, 5000.00, 10000.00),
(54, 'RTX33', 'P001', 1, 10000.00, 5000.00, 10000.00),
(55, 'RTX34', 'P001', 1, 10000.00, 5000.00, 10000.00),
(56, 'RTX35', 'P001', 1, 10000.00, 5000.00, 10000.00),
(57, 'RTX36', 'P001', 1, 10000.00, 5000.00, 10000.00),
(58, 'RTX37', 'P001', 1, 10000.00, 5000.00, 10000.00),
(59, 'RTX38', 'P001', 1, 10000.00, 5000.00, 10000.00),
(60, 'RTX39', 'P001', 1, 10000.00, 5000.00, 10000.00),
(61, 'RTX40', 'P001', 1, 10000.00, 5000.00, 10000.00),
(62, 'RTX41', 'P001', 1, 10000.00, 5000.00, 10000.00),
(63, 'RTX42', 'P001', 1, 10000.00, 5000.00, 10000.00),
(64, 'RTX43', 'P001', 1, 10000.00, 5000.00, 10000.00),
(65, 'RTX44', 'P011', 1, 6000.00, 5000.00, 6000.00),
(66, 'RTX45', 'P001', 1, 10000.00, 5000.00, 10000.00),
(67, 'RTX46', 'P011', 1, 6000.00, 5000.00, 6000.00),
(68, 'RTX47', 'P001', 1, 10000.00, 5000.00, 10000.00),
(69, 'RTX48', 'P011', 1, 6000.00, 5000.00, 6000.00),
(70, 'RTX49', 'P001', 1, 10000.00, 5000.00, 10000.00),
(71, 'RTX50', 'P011', 1, 6000.00, 5000.00, 6000.00),
(72, 'RTX51', 'P001', 1, 10000.00, 5000.00, 10000.00),
(73, 'RTX52', 'P001', 1, 10000.00, 5000.00, 10000.00),
(74, 'RTX53', 'P011', 1, 6000.00, 5000.00, 6000.00),
(75, 'RTX54', 'P001', 1, 10000.00, 5000.00, 10000.00),
(76, 'RTX55', 'P011', 1, 6000.00, 5000.00, 6000.00),
(77, 'RTX56', 'P001', 1, 10000.00, 5000.00, 10000.00),
(78, 'RTX57', 'P001', 1, 10000.00, 5000.00, 10000.00),
(79, 'RTX58', 'P001', 1, 10000.00, 5000.00, 10000.00),
(80, 'RTX59', 'P001', 1, 10000.00, 5000.00, 10000.00),
(81, 'RTX60', 'P003', 1, 6000.00, 5000.00, 6000.00),
(82, 'RTX61', 'P003', 1, 6000.00, 10000.00, 6000.00),
(83, 'RTX62', 'P001', 1, 1200.00, 1000.00, 1200.00),
(84, 'RTX63', 'P001', 1, 1200.00, 2000.00, 1200.00),
(85, 'RTX64', 'P003', 1, 6000.00, 5000.00, 6000.00),
(86, 'RTX65', 'P003', 1, 6000.00, 5000.00, 6000.00),
(87, 'RTX66', 'P003', 1, 6000.00, 1000.00, 6000.00),
(88, 'RTX67', 'P003', 1, 6000.00, 5000.00, 6000.00),
(89, 'RTX68', 'P003', 1, 120.00, 200.00, 120.00),
(90, 'RTX69', 'P003', 1, 120.00, 200.00, 120.00),
(91, 'RTX70', 'P003', 1, 120.00, 200.00, 120.00),
(92, 'RTX71', 'P003', 1, 120.00, 100.00, 120.00),
(93, 'RTX72', 'P003', 1, 240.00, 200.00, 240.00),
(94, 'RTX73', 'P003', 1, 120.00, 100.00, 120.00),
(95, 'RTX74', 'P004', 1, 120.00, 100.00, 120.00),
(96, 'RTX75', 'P003', 1, 360.00, 300.00, 360.00),
(97, 'RTX76', 'P003', 1, 240.00, 200.00, 240.00),
(98, 'RTX77', 'P003', 1, 120.00, 100.00, 120.00),
(99, 'RTX78', 'P014', 1, 120.00, 100.00, 120.00),
(100, 'RTX79', 'P003', 1, 120.00, 100.00, 120.00),
(101, 'RTX80', 'P003', 1, 120.00, 100.00, 120.00),
(102, 'RTX81', 'P003', 1, 120.00, 100.00, 120.00),
(103, 'RTX82', 'P003', 1, 120.00, 100.00, 120.00),
(104, 'RTX83', 'P003', 1, 120.00, 100.00, 120.00),
(105, 'RTX84', 'P003', 1, 120.00, 100.00, 120.00),
(106, 'RTX85', 'P003', 1, 120.00, 100.00, 120.00),
(107, 'RTX86', 'P003', 1, 120.00, 100.00, 120.00),
(108, 'RTX87', 'P003', 1, 120.00, 100.00, 120.00),
(109, 'RTX88', 'P003', 1, 120.00, 100.00, 120.00),
(110, 'RTX89', 'P003', 1, 120.00, 100.00, 120.00),
(111, 'RTX90', 'P003', 1, 120.00, 100.00, 120.00),
(112, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(113, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(114, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(115, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(116, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(117, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(118, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(119, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(120, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(121, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(122, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(123, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(124, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(125, 'RTX91', 'P003', 1, 120.00, 100.00, 120.00),
(126, 'RTX92', 'P003', 1, 120.00, 100.00, 120.00),
(127, 'RTX93', 'P003', 2, 240.00, 100.00, 120.00),
(128, 'RTX94', 'P003', 1, 120.00, 100.00, 120.00),
(129, 'RTX95', 'P001', 1, 18000.00, 15000.00, 18000.00),
(130, 'RTX96', 'P002', 1, 3600.00, 3000.00, 3600.00),
(131, 'RTX96', 'P002', 1, 3600.00, 3000.00, 3600.00),
(132, 'RTX96', 'P002', 1, 3600.00, 3000.00, 3600.00),
(133, 'RTX96', 'P002', 1, 3600.00, 3000.00, 3600.00),
(134, 'RTX97', 'P002', 1, 3600.00, 3000.00, 3600.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `laporanpembelian`
-- (See below for the actual view)
--
CREATE TABLE `laporanpembelian` (
`id_pembelian` int(11)
,`total` decimal(10,2)
,`id_produk` varchar(11)
,`id_penjualan` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` int(11) NOT NULL,
  `id_pemasok` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `tanggal_pembelian` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_pengguna` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `id_pemasok`, `total`, `tanggal_pembelian`, `id_pengguna`) VALUES
(1, 7, 150000.00, '2025-03-31 17:00:00', 2),
(3, 11, 250000.00, '2025-04-04 17:00:00', 2),
(8, 7, 10000.00, '2025-05-06 16:14:47', 4),
(9, 7, 50000.00, '2025-05-07 01:33:24', 4),
(10, 7, 70000.00, '2025-05-07 02:16:29', 4),
(11, 7, 15000.00, '2025-05-13 05:47:08', 4),
(12, 7, 1000.00, '2025-05-13 06:03:00', 4),
(13, 7, 2000.00, '2025-05-13 06:03:20', 4),
(14, 8, 10000.00, '2025-05-13 06:05:11', 4),
(15, 8, 10000.00, '2025-05-13 06:06:16', 4),
(16, 7, 3000.00, '2025-05-13 06:29:43', 4),
(17, 8, 6000.00, '2025-05-13 06:39:52', 4),
(18, 8, 300.00, '2025-05-13 07:06:15', 4),
(19, 8, 100.00, '2025-05-13 07:27:06', 4),
(20, 8, 400.00, '2025-05-13 07:28:14', 4),
(21, 8, 300.00, '2025-05-13 07:36:01', 4),
(22, 8, 200.00, '2025-05-13 07:44:39', 4),
(23, 8, 300.00, '2025-05-13 08:09:13', 4),
(24, 8, 200.00, '2025-05-13 08:13:32', 4),
(25, 8, 100.00, '2025-05-13 08:17:30', 4),
(26, 7, 100.00, '2025-05-15 08:33:23', 4),
(27, 8, 1100.00, '2025-05-15 19:03:15', 4),
(28, 8, 2000.00, '2025-05-15 21:23:08', 4),
(29, 7, 15000.00, '2025-05-20 09:10:41', 4),
(30, 7, 15000.00, '2025-05-20 09:31:19', 4),
(31, 7, 30000.00, '2025-05-21 02:47:26', 4);

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Master','Admin') NOT NULL,
  `rfid` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `username`, `password`, `role`, `rfid`) VALUES
(2, 'Vino', 'Vino123NK', '', NULL),
(3, 'Rian', 'Rian123OP', '', NULL),
(4, 'Rina', 'Rina123', 'Master', '3960193337'),
(5, 'Bagas', 'Bagas123', 'Admin', 'RF470498');

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` varchar(10) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `bayar` decimal(10,2) NOT NULL,
  `kembalian` decimal(10,2) NOT NULL,
  `tanggal_transaksi` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_pengguna` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `total`, `bayar`, `kembalian`, `tanggal_transaksi`, `id_pengguna`) VALUES
('', 70000.00, 0.00, 0.00, '2025-04-23 01:39:55', 4),
('RTX01', 2000.00, 0.00, 0.00, '2025-04-20 03:30:32', 4),
('RTX02', 2200.00, 0.00, 0.00, '2025-04-20 03:52:53', 4),
('RTX03', 2000.00, 0.00, 0.00, '2025-04-20 03:55:02', 4),
('RTX04', 10000.00, 0.00, 0.00, '2025-04-23 01:37:58', 4),
('RTX05', 120000.00, 0.00, 0.00, '2025-04-23 01:42:25', 4),
('RTX06', 10000.00, 0.00, 0.00, '2025-04-23 02:00:34', 4),
('RTX07', 10000.00, 0.00, 0.00, '2025-04-23 02:07:29', 4),
('RTX08', 10000.00, 0.00, 0.00, '2025-04-23 02:14:33', 4),
('RTX09', 20000.00, 0.00, 0.00, '2025-04-23 02:14:50', 4),
('RTX10', 10000.00, 0.00, 0.00, '2025-04-23 02:31:15', 4),
('RTX11', 20000.00, 0.00, 0.00, '2025-04-23 02:32:09', 4),
('RTX12', 20000.00, 0.00, 0.00, '2025-04-23 02:33:29', 4),
('RTX13', 20000.00, 0.00, 0.00, '2025-04-23 02:34:24', 4),
('RTX14', 30000.00, 0.00, 0.00, '2025-05-06 04:12:30', 4),
('RTX15', 40000.00, 0.00, 0.00, '2025-05-06 04:16:21', 4),
('RTX16', 20000.00, 50000.00, 30000.00, '2025-05-06 16:03:21', 4),
('RTX17', 10000.00, 20000.00, 10000.00, '2025-05-06 16:12:05', 4),
('RTX18', 30000.00, 50000.00, 20000.00, '2025-05-08 07:00:37', 4),
('RTX19', 10000.00, 20000.00, 10000.00, '2025-05-08 07:01:37', 4),
('RTX20', 10000.00, 20000.00, 10000.00, '2025-05-08 07:04:12', 4),
('RTX21', 20000.00, 20000.00, 10000.00, '2025-05-08 07:07:57', 4),
('RTX22', 20000.00, 50000.00, 30000.00, '2025-05-08 07:10:14', 4),
('RTX23', 10000.00, 50000.00, 30000.00, '2025-05-08 07:11:02', 4),
('RTX24', 10000.00, 60000.00, 50000.00, '2025-05-08 07:13:54', 4),
('RTX25', 20000.00, 50000.00, 30000.00, '2025-05-08 07:20:01', 4),
('RTX26', 10000.00, 20000.00, 10000.00, '2025-05-08 07:24:09', 4),
('RTX27', 10000.00, 20000.00, 10000.00, '2025-05-08 07:25:16', 4),
('RTX28', 10000.00, 20000.00, 10000.00, '2025-05-08 07:26:30', 4),
('RTX29', 10000.00, 20000.00, 10000.00, '2025-05-08 07:27:32', 4),
('RTX30', 10000.00, 50000.00, 40000.00, '2025-05-08 07:31:04', 4),
('RTX31', 10000.00, 20000.00, 10000.00, '2025-05-08 07:48:33', 4),
('RTX32', 10000.00, 20000.00, 10000.00, '2025-05-08 07:49:22', 4),
('RTX33', 10000.00, 20000.00, 10000.00, '2025-05-08 07:54:25', 4),
('RTX34', 10000.00, 20000.00, 10000.00, '2025-05-08 07:56:55', 4),
('RTX35', 10000.00, 20000.00, 10000.00, '2025-05-08 07:57:29', 4),
('RTX36', 10000.00, 20000.00, 10000.00, '2025-05-08 07:58:56', 4),
('RTX37', 10000.00, 30000.00, 20000.00, '2025-05-08 08:00:35', 4),
('RTX38', 10000.00, 20000.00, 10000.00, '2025-05-08 08:02:50', 4),
('RTX39', 10000.00, 20000.00, 10000.00, '2025-05-08 08:03:59', 4),
('RTX40', 10000.00, 20000.00, 10000.00, '2025-05-08 08:11:42', 4),
('RTX41', 10000.00, 20000.00, 10000.00, '2025-05-08 08:14:15', 4),
('RTX42', 10000.00, 20000.00, 10000.00, '2025-05-08 08:15:34', 4),
('RTX43', 10000.00, 20000.00, 10000.00, '2025-05-08 08:19:40', 4),
('RTX44', 6000.00, 20000.00, 14000.00, '2025-05-08 08:21:20', 4),
('RTX45', 10000.00, 20000.00, 10000.00, '2025-05-08 08:22:27', 4),
('RTX46', 6000.00, 10000.00, 4000.00, '2025-05-08 08:23:39', 4),
('RTX47', 10000.00, 10000.00, 4000.00, '2025-05-08 08:24:55', 4),
('RTX48', 6000.00, 10000.00, 4000.00, '2025-05-08 08:26:11', 4),
('RTX49', 10000.00, 10000.00, 4000.00, '2025-05-08 08:26:40', 4),
('RTX50', 6000.00, 10000.00, 4000.00, '2025-05-08 08:28:02', 4),
('RTX51', 10000.00, 10000.00, 4000.00, '2025-05-08 08:28:26', 4),
('RTX52', 10000.00, 10000.00, 4000.00, '2025-05-08 08:29:01', 4),
('RTX53', 6000.00, 10000.00, 4000.00, '2025-05-08 08:31:11', 4),
('RTX54', 10000.00, 10000.00, 0.00, '2025-05-08 08:32:54', 4),
('RTX55', 6000.00, 10000.00, 4000.00, '2025-05-08 08:36:00', 4),
('RTX56', 10000.00, 10000.00, 4000.00, '2025-05-08 08:36:40', 4),
('RTX57', 10000.00, 10000.00, 4000.00, '2025-05-08 08:37:42', 4),
('RTX58', 10000.00, 10000.00, 0.00, '2025-05-13 05:29:17', 4),
('RTX59', 10000.00, 10000.00, 0.00, '2025-05-13 05:44:32', 4),
('RTX60', 6000.00, 10000.00, 4000.00, '2025-05-13 06:06:48', 4),
('RTX61', 6000.00, 10000.00, 4000.00, '2025-05-13 06:07:08', 4),
('RTX62', 1200.00, 10000.00, 8800.00, '2025-05-13 06:09:03', 4),
('RTX63', 1200.00, 10000.00, 8800.00, '2025-05-13 06:09:38', 4),
('RTX64', 6000.00, 10000.00, 4000.00, '2025-05-13 06:52:01', 4),
('RTX65', 6000.00, 10000.00, 4000.00, '2025-05-13 06:52:16', 4),
('RTX66', 6000.00, 10000.00, 4000.00, '2025-05-13 06:53:35', 4),
('RTX67', 6000.00, 10000.00, 4000.00, '2025-05-13 07:03:26', 4),
('RTX68', 120.00, 2000.00, 1880.00, '2025-05-13 07:21:02', 4),
('RTX69', 120.00, 1000.00, 880.00, '2025-05-13 07:24:36', 4),
('RTX70', 120.00, 200.00, 80.00, '2025-05-13 07:28:42', 4),
('RTX71', 120.00, 200.00, 80.00, '2025-05-13 07:36:33', 4),
('RTX72', 240.00, 200.00, 80.00, '2025-05-13 07:37:08', 4),
('RTX73', 120.00, 200.00, 80.00, '2025-05-13 07:45:28', 4),
('RTX74', 240.00, 400.00, 160.00, '2025-05-13 07:46:39', 4),
('RTX75', 720.00, 1000.00, 280.00, '2025-05-13 08:11:44', 4),
('RTX76', 240.00, 1200.00, 960.00, '2025-05-13 08:16:46', 4),
('RTX77', 120.00, 200.00, 80.00, '2025-05-13 08:17:53', 4),
('RTX78', 120.00, 200.00, 80.00, '2025-05-15 19:01:46', 4),
('RTX79', 120.00, 1000.00, 880.00, '2025-05-15 19:03:27', 4),
('RTX80', 120.00, 200.00, 80.00, '2025-05-15 19:05:41', 4),
('RTX81', 120.00, 200.00, 80.00, '2025-05-15 19:26:46', 4),
('RTX82', 120.00, 200.00, 80.00, '2025-05-15 19:46:07', 4),
('RTX83', 120.00, 200.00, 80.00, '2025-05-15 19:53:03', 4),
('RTX84', 120.00, 200.00, 80.00, '2025-05-15 20:02:17', 4),
('RTX85', 120.00, 200.00, 80.00, '2025-05-15 20:06:33', 4),
('RTX86', 120.00, 200.00, 80.00, '2025-05-15 20:24:17', 4),
('RTX87', 120.00, 200.00, 80.00, '2025-05-15 20:56:52', 4),
('RTX88', 120.00, 200.00, 80.00, '2025-05-15 21:12:11', 4),
('RTX89', 120.00, 200.00, 80.00, '2025-05-15 21:14:54', 4),
('RTX90', 120.00, 200.00, 80.00, '2025-05-15 21:23:21', 4),
('RTX91', 1680.00, 2000.00, 320.00, '2025-05-16 05:58:02', 4),
('RTX92', 120.00, 2000.00, 320.00, '2025-05-16 05:58:42', 4),
('RTX93', 240.00, 2000.00, 320.00, '2025-05-16 06:03:42', 4),
('RTX94', 120.00, 200.00, 80.00, '2025-05-19 03:50:23', 4),
('RTX95', 18000.00, 20000.00, 2000.00, '2025-05-21 02:37:31', 4),
('RTX96', 14400.00, 20000.00, 5600.00, '2025-05-21 02:49:42', 4),
('RTX97', 3600.00, 20000.00, 16400.00, '2025-05-21 06:12:01', 4);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` varchar(11) NOT NULL,
  `nama_produk` varchar(100) NOT NULL,
  `barcode` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `barcode`) VALUES
('P001', 'Beras', '8732584794902'),
('P002', 'Buku', '8995757044822');

-- --------------------------------------------------------

--
-- Table structure for table `return_pembelian`
--

CREATE TABLE `return_pembelian` (
  `id_retur` int(11) NOT NULL,
  `id_pembelian` int(11) DEFAULT NULL,
  `id_produk` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `alasan` varchar(255) NOT NULL,
  `tanggal_return` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stok_produk`
--

CREATE TABLE `stok_produk` (
  `id_stok` int(11) NOT NULL,
  `id_produk` varchar(11) NOT NULL,
  `id_pembelian` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga_beli` decimal(10,2) NOT NULL,
  `harga_jual` decimal(10,0) NOT NULL,
  `tanggal_kedaluwarsa` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stok_produk`
--

INSERT INTO `stok_produk` (`id_stok`, `id_produk`, `id_pembelian`, `stok`, `harga_beli`, `harga_jual`, `tanggal_kedaluwarsa`) VALUES
(29, 'P001', 29, 0, 15000.00, 18000, '2025-05-29'),
(30, 'p001', 30, 0, 15000.00, 18000, '2025-05-30'),
(31, 'p002', 31, 0, 3000.00, 3600, '2025-05-28');

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE `suplier` (
  `id_pemasok` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kontak` varchar(13) DEFAULT NULL,
  `Alamat` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`id_pemasok`, `nama`, `kontak`, `Alamat`) VALUES
(7, 'PT. Susu Indonesia', '031-987654', 'Jl. Susu No. 8, Sura'),
(8, 'Toko Gula Murni', '061-234567', 'Jl. Gula Pasir No. 3'),
(9, 'Distributor Makanan Sehat', '0274-567890', 'Jl. Sehat No. 20, Yo'),
(10, 'Toko Sembako Sukses', '0411-345678', 'Jl. Sembako No. 11, '),
(11, 'Tepung Terigu Tiga Roda', '0341-876543', 'Jl. Terigu No. 15, M'),
(12, 'PT. Makanan Enak', '024-789012', 'Jl. Makanan Enak No.'),
(13, 'Susu Segar Nusantara', '0361-123789', 'Jl. Segar No. 7, Bal');

-- --------------------------------------------------------

--
-- Structure for view `laporanpembelian`
--
DROP TABLE IF EXISTS `laporanpembelian`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `laporanpembelian`  AS SELECT `dp`.`id_pembelian` AS `id_pembelian`, `p`.`total` AS `total`, `dp`.`id_produk` AS `id_produk`, `p`.`id_pembelian` AS `id_penjualan` FROM ((`detail_pembelian` `dp` join `produk` `prd` on(`dp`.`id_produk` = `prd`.`id_produk`)) join `pembelian` `p` on(`dp`.`id_pembelian` = `p`.`id_pembelian`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang_rusak`
--
ALTER TABLE `barang_rusak`
  ADD PRIMARY KEY (`id_rusak`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_pembelian` (`id_pembelian`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_stok` (`id_stok`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `id_pemasok` (`id_pemasok`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `idxPembelian` (`id_pembelian`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `idxPengguna` (`username`);

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_pengguna` (`id_pengguna`),
  ADD KEY `idxPenjualan` (`id_penjualan`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `return_pembelian`
--
ALTER TABLE `return_pembelian`
  ADD PRIMARY KEY (`id_retur`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_pembelian` (`id_pembelian`);

--
-- Indexes for table `stok_produk`
--
ALTER TABLE `stok_produk`
  ADD PRIMARY KEY (`id_stok`),
  ADD KEY `id_produk` (`id_produk`),
  ADD KEY `id_pembelian` (`id_pembelian`);

--
-- Indexes for table `suplier`
--
ALTER TABLE `suplier`
  ADD PRIMARY KEY (`id_pemasok`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang_rusak`
--
ALTER TABLE `barang_rusak`
  MODIFY `id_rusak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `id_pembelian` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `return_pembelian`
--
ALTER TABLE `return_pembelian`
  MODIFY `id_retur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `stok_produk`
--
ALTER TABLE `stok_produk`
  MODIFY `id_stok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `suplier`
--
ALTER TABLE `suplier`
  MODIFY `id_pemasok` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang_rusak`
--
ALTER TABLE `barang_rusak`
  ADD CONSTRAINT `barang_rusak_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`);

--
-- Constraints for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`) ON DELETE CASCADE,
  ADD CONSTRAINT `detail_pembelian_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  ADD CONSTRAINT `detail_pembelian_ibfk_3` FOREIGN KEY (`id_stok`) REFERENCES `stok_produk` (`id_stok`);

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`),
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`);

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`id_pemasok`) REFERENCES `suplier` (`id_pemasok`) ON DELETE CASCADE,
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`) ON DELETE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_pengguna`) REFERENCES `pengguna` (`id_pengguna`);

--
-- Constraints for table `return_pembelian`
--
ALTER TABLE `return_pembelian`
  ADD CONSTRAINT `return_pembelian_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  ADD CONSTRAINT `return_pembelian_ibfk_3` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`);

--
-- Constraints for table `stok_produk`
--
ALTER TABLE `stok_produk`
  ADD CONSTRAINT `stok_produk_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`),
  ADD CONSTRAINT `stok_produk_ibfk_2` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
