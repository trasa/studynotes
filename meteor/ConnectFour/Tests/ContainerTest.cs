using System;
using ConnectFour.Game;
using NUnit.Framework;

namespace ConnectFour.Tests
{
    [TestFixture]
    public class ContainerTest
    {
        private Container container;

        [SetUp]
        public void SetUp()
        {
            container = new Container();
        }

        [Test]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void AddPiece_InvalidColumn()
        {
            container.AddPiece(PieceColor.Black, (uint) container.ColumnCount);
        }

        [Test]
        [ExpectedException(typeof(IllegalPlacementException))]
        public void AddNonePiece()
        {
            container.AddPiece(PieceColor.None, 0);
        }

        [Test]
        public void AddPiecesUntilFull()
        {
            // add legitimate pieces for first 4 spaces
            for (int i = 0; i < ContainerColumn.MaxHeight - 1; i++)
            {
                container.AddPiece(PieceColor.Black, 0);
                Assert.False(container.IsColumnFull(0), "column should not be full");
            }
            // add one more to fill the column up
            container.AddPiece(PieceColor.Black, 0);
            Assert.True(container.IsColumnFull(0), "column should be full");

            // now adding one more fails us - column was full.
            bool failed = false;
            try
            {
                container.AddPiece(PieceColor.Black, 0);
            }
            catch (IllegalPlacementException)
            {
                failed = true;
            }
            Assert.True(failed, "should have gotten IllegalPlacementException");
        }

        [Test]
        public void GetPiece_EmptyColumn()
        {
            PieceColor piece = container.GetPiece(0, 0);
            Assert.AreEqual(PieceColor.None, piece);
        }

        [Test]
        public void GetPiece_NotEmpty()
        {
            container.AddPiece(PieceColor.Red, 0);
            Assert.AreEqual(PieceColor.Red, container.GetPiece(0, 0));
        }

        [Test]
        public void GetPiece_Stack()
        {
            container.AddPiece(PieceColor.Red, 0); //0
            container.AddPiece(PieceColor.Black, 0);//1
            container.AddPiece(PieceColor.Red, 0);//2
            container.AddPiece(PieceColor.Black, 0);//3
            container.AddPiece(PieceColor.Red, 0);//4
            container.AddPiece(PieceColor.Black, 0);//5

            Assert.AreEqual(PieceColor.Red, container.GetPiece(0, 0));
            Assert.AreEqual(PieceColor.Black, container.GetPiece(0, 1));
            Assert.AreEqual(PieceColor.Red, container.GetPiece(0, 2));
            Assert.AreEqual(PieceColor.Black, container.GetPiece(0, 3));
            Assert.AreEqual(PieceColor.Red, container.GetPiece(0, 4));
            Assert.AreEqual(PieceColor.Black, container.GetPiece(0, 5));
        }

        [Test]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void GetPiece_IllegalRow()
        {
            container.GetPiece(0, ContainerColumn.MaxHeight);
        }

        [Test]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void GetPiece_IllegalColumn()
        {
            container.GetPiece((uint) container.ColumnCount, 0);
        }

        [Test]
        public void IsFull()
        {
            for (uint col = 0; col < container.ColumnCount; col++)
            {
                Assert.False(container.IsContainerFull);
                for (uint row = 0; row < ContainerColumn.MaxHeight; row++)
                {
                    container.AddPiece(PieceColor.Black, col);
                }                
            }
            Assert.True(container.IsContainerFull);            
        }

        [Test]
        public void GetLeft()
        {
            container.AddPiece(PieceColor.Black, 0);
            container.AddPiece(PieceColor.Red, 1);
            // this sums the pieces to the left, not including the space we're starting at.
            Assert.AreEqual(0, container.GetPieceCountLeft(PieceColor.Red, 1, 0));
            Assert.AreEqual(0, container.GetPieceCountLeft(PieceColor.Black, 0, 0));
            Assert.AreEqual(1, container.GetPieceCountLeft(PieceColor.Red, 2, 0));
        }

        [Test]
        public void GetRight()
        {
            container.AddPiece(PieceColor.Black, (uint) (container.ColumnCount - 2));  // 5
            container.AddPiece(PieceColor.Red, (uint)(container.ColumnCount - 1)); // 6

            // to the right of this is the edge
            Assert.AreEqual(0, container.GetPieceCountRight(PieceColor.Red, container.ColumnCount - 1, 0));
            // to the right of this is a red
            Assert.AreEqual(0, container.GetPieceCountRight(PieceColor.Black, container.ColumnCount - 2, 0));
            // to the right of this is a red
            Assert.AreEqual(1, container.GetPieceCountRight(PieceColor.Red, container.ColumnCount - 2, 0));
        }
        
    }
}
