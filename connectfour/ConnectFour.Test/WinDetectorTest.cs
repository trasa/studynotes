using ConnectFour.Game;
using NUnit.Framework;

namespace ConnectFour.Tests
{
    [TestFixture]
    public class WinDetectorTest
    {
        private Container container;

        [SetUp]
        public void SetUp()
        {
            container = new Container();
        }

        [Test]
        public void HorizontalWin()
        {
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            Assert.False(container.AddPiece(PieceColor.Red, 1));
            Assert.False(container.AddPiece(PieceColor.Red, 3));

            Assert.True(container.AddPiece(PieceColor.Red, 2));
        }

        [Test]
        public void VerticalWin()
        {
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            
            Assert.True(container.AddPiece(PieceColor.Red, 0));
        }

        [Test]
        public void PositiveDiagonalWin()
        {
            /*   0 1 2 3 4 5
             * 5
             * 4
             * 3       R
             * 2     R b
             * 1   R b b
             * 0 R b b b
             */
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            
            Assert.False(container.AddPiece(PieceColor.Black, 1));
            // insert this red last ..

            Assert.False(container.AddPiece(PieceColor.Black, 2));
            Assert.False(container.AddPiece(PieceColor.Black, 2));
            Assert.False(container.AddPiece(PieceColor.Red, 2));

            Assert.False(container.AddPiece(PieceColor.Black, 3));
            Assert.False(container.AddPiece(PieceColor.Black, 3));
            Assert.False(container.AddPiece(PieceColor.Black, 3));
            Assert.False(container.AddPiece(PieceColor.Red, 3));

            Assert.True(container.AddPiece(PieceColor.Red, 1));

        }

        [Test]
        public void NegativeDiagonalWin()
        {
            /*   0 1 2 3 4 5
             * 5
             * 4
             * 3 R
             * 2 b R
             * 1 b b R 
             * 0 b b b R
             */         
            Assert.False(container.AddPiece(PieceColor.Black, 0));
            Assert.False(container.AddPiece(PieceColor.Black, 0));
            Assert.False(container.AddPiece(PieceColor.Black, 0));
            Assert.False(container.AddPiece(PieceColor.Red, 0));
            
            Assert.False(container.AddPiece(PieceColor.Black, 1));
            Assert.False(container.AddPiece(PieceColor.Black, 1));
            

            Assert.False(container.AddPiece(PieceColor.Black, 2));
            Assert.False(container.AddPiece(PieceColor.Red, 2));

            Assert.False(container.AddPiece(PieceColor.Red, 3));

            Assert.True(container.AddPiece(PieceColor.Red, 1));
        }
    }
}
