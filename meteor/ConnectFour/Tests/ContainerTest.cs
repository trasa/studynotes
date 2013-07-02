using System;
using ConnectFour.GameApi;
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
    }
}
