-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2024 at 10:41 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `duan_datdoan`
--

-- --------------------------------------------------------

--
-- Table structure for table `dt_banan`
--

CREATE TABLE `dt_banan` (
  `maban` int(11) NOT NULL,
  `tenban` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_banan`
--

INSERT INTO `dt_banan` (`maban`, `tenban`) VALUES
(1, 'Bàn số 1 '),
(2, 'Bàn số 2 '),
(3, 'Bàn số 3 '),
(4, 'Bàn số 4 ');

-- --------------------------------------------------------

--
-- Table structure for table `dt_doan`
--

CREATE TABLE `dt_doan` (
  `madoan` int(11) NOT NULL,
  `tendoan` varchar(255) DEFAULT NULL,
  `giadoan` int(11) DEFAULT NULL,
  `maloai` int(11) DEFAULT NULL,
  `thongtin` varchar(255) DEFAULT NULL,
  `anh` varchar(255) DEFAULT NULL,
  `madoanphu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_doan`
--

INSERT INTO `dt_doan` (`madoan`, `tendoan`, `giadoan`, `maloai`, `thongtin`, `anh`, `madoanphu`) VALUES
(1, 'cơm rang', 30000, 1, 'Cơm rất ngon', 'https://lh3.googleusercontent.com', NULL),
(2, 'bún chả', 30000, 2, 'Bún rất ngon', 'https://bizweb.dktcdn.net', NULL),
(3, 'Xôi ngô', 15000, 3, 'Xôi rất dẻo và thơm', 'https://i-giadinh.vnecdn.net', NULL),
(4, 'Cháo thịt', 10000, 4, 'Cháo rất giàu dinh dưỡng', 'https://cdn.tgdd.vn/Files', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dt_doanphu`
--

CREATE TABLE `dt_doanphu` (
  `MaDoAnPhu` int(11) NOT NULL,
  `TenDoAnPhu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_doanphu`
--

INSERT INTO `dt_doanphu` (`MaDoAnPhu`, `TenDoAnPhu`) VALUES
(1, 'Giò'),
(2, 'Chả'),
(3, 'Nem'),
(4, 'Trứng');

-- --------------------------------------------------------

--
-- Table structure for table `dt_giohang`
--

CREATE TABLE `dt_giohang` (
  `masp` int(11) NOT NULL,
  `tensp` varchar(255) DEFAULT NULL,
  `tendoanphu` varchar(255) DEFAULT NULL,
  `giasp` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `anhsp` varchar(255) DEFAULT NULL,
  `matv` int(11) DEFAULT NULL,
  `giadoan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_giohang`
--

INSERT INTO `dt_giohang` (`masp`, `tensp`, `tendoanphu`, `giasp`, `soluong`, `anhsp`, `matv`, `giadoan`) VALUES
(1, 'bún chả', 'Giò', 30000, 2, 'https://bizweb.dktcdn.net', NULL, NULL),
(2, 'cơm rang', 'Chả', 30000, 1, 'https://lh3.googleusercontent.com', NULL, NULL),
(3, 'Xôi ngô', 'Giò', 15000, 1, 'https://i-giadinh.vnecdn.net', NULL, NULL),
(4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `dt_hoadon`
--

CREATE TABLE `dt_hoadon` (
  `mahoadon` int(11) NOT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `hoten` varchar(100) DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `diachinhan` varchar(255) DEFAULT NULL,
  `thucdon` varchar(255) DEFAULT NULL,
  `ngaydathang` date DEFAULT NULL,
  `tongtien` decimal(10,2) DEFAULT NULL,
  `thanhtoan` varchar(50) DEFAULT NULL,
  `trangthai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_hoadon`
--

INSERT INTO `dt_hoadon` (`mahoadon`, `Email`, `hoten`, `SDT`, `diachinhan`, `thucdon`, `ngaydathang`, `tongtien`, `thanhtoan`, `trangthai`) VALUES
(1, 'an1911896@gmail.com', 'An', '0397209025', 'Bàn số 3', 'cơm rang', '2024-11-18', '90000.00', 'Đã thanh toán', 'Món ăn đã chế biến');

-- --------------------------------------------------------

--
-- Table structure for table `dt_loai`
--

CREATE TABLE `dt_loai` (
  `maloai` int(11) NOT NULL,
  `tenloai` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_loai`
--

INSERT INTO `dt_loai` (`maloai`, `tenloai`) VALUES
(1, 'cơm'),
(2, 'bún'),
(3, 'xôi'),
(4, 'cháo');

-- --------------------------------------------------------

--
-- Table structure for table `dt_nguoidung`
--

CREATE TABLE `dt_nguoidung` (
  `maTV` int(11) NOT NULL,
  `MaND` varchar(50) NOT NULL,
  `HoTen` varchar(100) DEFAULT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `NamSinh` year(4) DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `LoaiTaiKhoan` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dt_nguoidung`
--

INSERT INTO `dt_nguoidung` (`maTV`, `MaND`, `HoTen`, `MatKhau`, `Email`, `NamSinh`, `SDT`, `LoaiTaiKhoan`) VALUES
(1, 'admin', 'Quản lý', 'admin', 'helo@gmail.com', 2004, '012345678', 'admin'),
(2, 'nguoidung01', 'Người dùng', 'nguoidung01', 'nguoidung@gmail.com', 2005, '123456789', 'user'),
(3, 'daubep', 'Đầu bếp', 'daubep', 'daubep@gmail.com', 2002, '123456789', 'daubep'),
(4, 'an2908', 'An', '123456', 'an1911896@gmail.com', 2003, '0397209025', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dt_banan`
--
ALTER TABLE `dt_banan`
  ADD PRIMARY KEY (`maban`),
  ADD UNIQUE KEY `maban` (`maban`);

--
-- Indexes for table `dt_doan`
--
ALTER TABLE `dt_doan`
  ADD PRIMARY KEY (`madoan`),
  ADD KEY `fk_doan_loai` (`maloai`),
  ADD KEY `fk_doan_doanphu` (`madoanphu`),
  ADD KEY `madoan` (`madoan`) USING BTREE;

--
-- Indexes for table `dt_doanphu`
--
ALTER TABLE `dt_doanphu`
  ADD PRIMARY KEY (`MaDoAnPhu`);

--
-- Indexes for table `dt_giohang`
--
ALTER TABLE `dt_giohang`
  ADD PRIMARY KEY (`masp`),
  ADD UNIQUE KEY `masp` (`masp`),
  ADD KEY `fk_giohang_nguoidung` (`matv`),
  ADD KEY `fk_giohang_doan` (`giadoan`);

--
-- Indexes for table `dt_hoadon`
--
ALTER TABLE `dt_hoadon`
  ADD PRIMARY KEY (`mahoadon`),
  ADD KEY `fk_hoadon_nguoidung` (`Email`);

--
-- Indexes for table `dt_loai`
--
ALTER TABLE `dt_loai`
  ADD PRIMARY KEY (`maloai`);

--
-- Indexes for table `dt_nguoidung`
--
ALTER TABLE `dt_nguoidung`
  ADD PRIMARY KEY (`maTV`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dt_doan`
--
ALTER TABLE `dt_doan`
  MODIFY `madoan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dt_hoadon`
--
ALTER TABLE `dt_hoadon`
  MODIFY `mahoadon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `dt_loai`
--
ALTER TABLE `dt_loai`
  MODIFY `maloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `dt_nguoidung`
--
ALTER TABLE `dt_nguoidung`
  MODIFY `maTV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dt_doan`
--
ALTER TABLE `dt_doan`
  ADD CONSTRAINT `fk_doan_doanphu` FOREIGN KEY (`madoanphu`) REFERENCES `dt_doanphu` (`MaDoAnPhu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_doan_giohang` FOREIGN KEY (`madoan`) REFERENCES `dt_giohang` (`masp`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_doan_loai` FOREIGN KEY (`maloai`) REFERENCES `dt_loai` (`maloai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dt_giohang`
--
ALTER TABLE `dt_giohang`
  ADD CONSTRAINT `fk_giohang_nguoidung` FOREIGN KEY (`matv`) REFERENCES `dt_nguoidung` (`maTV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dt_hoadon`
--
ALTER TABLE `dt_hoadon`
  ADD CONSTRAINT `fk_hoadon_nguoidung` FOREIGN KEY (`Email`) REFERENCES `dt_nguoidung` (`Email`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
