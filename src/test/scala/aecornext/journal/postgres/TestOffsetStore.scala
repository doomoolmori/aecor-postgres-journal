package aecornext.journal.postgres

import aecornext.data.TagConsumer
import aecornext.journal.postgres.PostgresEventJournalQueries.OffsetStore
import cats.effect.IO

object TestOffsetStore {
  def apply(state: Map[TagConsumer, Offset]): IO[TestOffsetStore] =
    IO(
      new TestOffsetStore(
        scala.collection.concurrent.TrieMap(state.toVector: _*)))

}

class TestOffsetStore(
    store: scala.collection.concurrent.TrieMap[TagConsumer, Offset])
    extends OffsetStore[IO] {
  override def setValue(key: TagConsumer, value: Offset): IO[Unit] =
    IO(store.update(key, value))

  override def getValue(key: TagConsumer): IO[Option[Offset]] =
    IO(store.get(key))

  override def deleteValue(key: TagConsumer): IO[Unit] = IO(store -= key)
}
