using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
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
        public void AddPiece_InvalidColumn_Low()
        {
            container.AddPiece(PieceColor.Black, -1);
        }

        [Test]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void AddPiece_InvalidColumn_High()
        {
            container.AddPiece(PieceColor.Black, 10);
        }

        [Test]
        public void AddPiecesUntilFull()
        {
            // add legitimate pieces for first 4 spaces
            for (int i = 0; i < 5; i++)
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
    }
}
