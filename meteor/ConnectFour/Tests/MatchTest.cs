using System;
using ConnectFour.Game;
using NUnit.Framework;

namespace ConnectFour.Tests
{
    [TestFixture]
    public class MatchTest
    {
        private Match match;

        [SetUp]
        public void SetUp()
        {
            match = new Match();
        }
        [Test]
        public void PlayersSwapAfterMove()
        {
            Assert.AreEqual(PieceColor.Black, match.CurrentPlayer);
            match.AddPiece(0);
            Assert.AreEqual(PieceColor.Red, match.CurrentPlayer);
            match.AddPiece(0);
            Assert.AreEqual(PieceColor.Black, match.CurrentPlayer);
        }

        [Test]
        public void PlayerWins()
        {
            /*   0 1 2 3 4 5
             * 5
             * 4
             * 3
             * 2
             * 1 R R R
             * 0 B B B B
             */
            match.AddPiece(0);
            match.AddPiece(0);

            match.AddPiece(1);
            match.AddPiece(1);
            
            match.AddPiece(2);
            match.AddPiece(2);

            match.AddPiece(3);
            
            Assert.IsTrue(match.GameOver);
            Assert.AreEqual(PieceColor.Black, match.Winner);
        }

        [Test]
        public void Tie()
        {
            /*   0 1 2 3 4 5 6
             * 5 B R B R B R R 
             * 4 R B R B R B R 
             * 3 B R B R B R B 
             * 2 B R B R B R B 
             * 1 R B R B R B R 
             * 0 B R B R B R B 
             */
            match.AddPiece(0);
            match.AddPiece(0);
            match.AddPiece(0);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(1);
            match.AddPiece(1);
            match.AddPiece(1);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(2);
            match.AddPiece(2);
            match.AddPiece(2);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(3);
            match.AddPiece(3);
            match.AddPiece(3);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(4);
            match.AddPiece(4);
            match.AddPiece(4);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(5);
            match.AddPiece(5);
            match.AddPiece(5);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(6);
            match.AddPiece(6);
            match.AddPiece(6);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(1);
            match.AddPiece(1);
            match.AddPiece(1);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(0);
            match.AddPiece(0);
            match.AddPiece(0);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(3);
            match.AddPiece(3);
            match.AddPiece(3);
            Assert.IsFalse(match.GameOver);

            match.AddPiece(2);
            match.AddPiece(2);
            match.AddPiece(2);

            match.AddPiece(5);
            match.AddPiece(5);
            match.AddPiece(5);
            
            match.AddPiece(6);
            match.AddPiece(6);
            
            match.AddPiece(4);
            match.AddPiece(4);
            match.AddPiece(4);

            match.AddPiece(6);

            Console.Out.WriteLine(match.Container.ToString());
            Assert.IsTrue(match.GameOver);
            Assert.AreEqual(PieceColor.None, match.Winner);
        }
    }
}

