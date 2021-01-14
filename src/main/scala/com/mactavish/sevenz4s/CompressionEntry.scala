package com.mactavish.sevenz4s

import java.util.{Calendar, Date}

import net.sf.sevenzipjbinding._


sealed class CompressionEntry(val dataSize: Long, val source: ISequentialInStream)

final case class CompressionEntryGZip(
                                       override val dataSize: Long,
                                       override val source: ISequentialInStream,
                                       path: String,
                                       lastModificationTime: Date = Calendar.getInstance().getTime
                                     ) extends CompressionEntry(dataSize, source) {
}

final case class CompressionEntryBZip2(
                                        override val dataSize: Long,
                                        override val source: ISequentialInStream
                                      ) extends CompressionEntry(dataSize, source)

final case class CompressionEntryZip(
                                      override val dataSize: Long,
                                      override val source: ISequentialInStream,
                                      path: String,
                                      isDir: Boolean,
                                      lastModificationTime: Date = Calendar.getInstance().getTime,
                                      lastAccessTime: Date = Calendar.getInstance().getTime,
                                      creationTime: Date = Calendar.getInstance().getTime,
                                    ) extends CompressionEntry(dataSize, source)


final case class CompressionEntry7Z(
                                     override val dataSize: Long,
                                     override val source: ISequentialInStream,
                                     path: String,
                                     isDir: Boolean,
                                     lastModificationTime: Date = Calendar.getInstance().getTime,
                                     isAnti: Boolean = false //  If true delete corresponding file or directory during
                                     // extraction.
                                   ) extends CompressionEntry(dataSize, source)

final case class CompressionEntryTar(
                                      override val dataSize: Long,
                                      override val source: ISequentialInStream,
                                      path: String,
                                      isDir: Boolean,
                                      lastModificationTime: Date = Calendar.getInstance().getTime,
                                      user: String = "",
                                      group: String = "",
                                      symLink: String = null,
                                      hardLink: String = null
                                    ) extends CompressionEntry(dataSize, source)
