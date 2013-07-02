using System;
using ConnectFour.GameApi;
using NUnit.Framework;

namespace ConnectFour.Tests
{
    [TestFixture]
    public class ContainerColumnTest
    {
        private ContainerColumn column;

        [SetUp]
        public void SetUp()
        {
            column = new ContainerColumn();
        }

        [Test]
        public void FillColumn()
        {
            for (int i = 0; i < ContainerColumn.MaxHeight - 1; i++)
            {
                column.AddPiece(PieceColor.Red);
                Assert.False(column.IsFull, "not full yet");
            }
                
            column.AddPiece(PieceColor.Red);
            Assert.True(column.IsFull, "its full now");
        }

        [Test]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void GetPiece_OutOfRange()
        {
            column.GetPiece(ContainerColumn.MaxHeight);
        }

        [Test]
        public void GetPiece_Empty()
        {
            for (uint i = 0; i < ContainerColumn.MaxHeight; i++)
            {
                Assert.AreEqual(PieceColor.None, column.GetPiece(i));
            }
        }

        [Test]
        public void GetPieces()
        {
            column.AddPiece(PieceColor.Red); 
            column.AddPiece(PieceColor.Black); 
            column.AddPiece(PieceColor.Red); 
            column.AddPiece(PieceColor.Black); 
            column.AddPiece(PieceColor.Red); 
            column.AddPiece(PieceColor.Black);

            Assert.AreEqual(PieceColor.Red, column.GetPiece(0));
            Assert.AreEqual(PieceColor.Black, column.GetPiece(1));
            Assert.AreEqual(PieceColor.Red, column.GetPiece(2));
            Assert.AreEqual(PieceColor.Black, column.GetPiece(3));
            Assert.AreEqual(PieceColor.Red, column.GetPiece(4));
            Assert.AreEqual(PieceColor.Black, column.GetPiece(5));
        }
    }
}
